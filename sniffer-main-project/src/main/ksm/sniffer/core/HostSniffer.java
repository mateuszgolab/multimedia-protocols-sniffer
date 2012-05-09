package ksm.sniffer.core;

import java.io.IOException;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;
import ksm.sniffer.core.exception.HostException;
import ksm.sniffer.core.exception.NetworkInterfaceException;
import ksm.sniffer.core.packet.ModulePacket;
import ksm.sniffer.core.packet.receiver.PacketsStorage;
import ksm.sniffer.module.api.net.HostIndex;
import ksm.sniffer.module.api.net.HostInformation;

import org.apache.log4j.Logger;

/**
 * Class representing sniffer receiving packets sent from specified host.
 */
@SuppressWarnings("restriction")
public class HostSniffer implements Runnable {
    
    /**
     * Network interface not found exception text.
     */
    public static final String NETWORK_INTERFACE_NOT_FOUND_EXCEPTION = "Network interface could not be found";
    /**
     * Network interface not opened exception text.
     */
    public static final String NETWORK_INTERFACE_NOT_OPENED_EXCEPTION = "Network interface could not be opened";
    private static final int MAX_BYTES_TRANSFERED_AT_ONCE = 100000;
    private static final boolean IS_PROMISCUOUS = true;
    private static final int TIMEOUT = 10;
    private static final Logger LOG = Logger.getLogger(HostSniffer.class);
    
    private final PacketsStorage packetsStorage = new PacketsStorage();
    private final HostInformation host;
    private JpcapCaptor captor;
    private NetworkInterface networkInterface;
    
    
    /**
     * Constructs HostSniffer listening on specified host.
     * @param host sniffed host
     */
    public HostSniffer(final HostInformation host) {
        assert host != null;
        this.host = host;
    }
    
    /**
     * Constructs HostSniffer listening on specified host.
     * @param host sniffed host
     * @param networkInterface network interface
     */
    public HostSniffer(final HostInformation host, final NetworkInterface networkInterface) {
        assert host != null;
        this.networkInterface = networkInterface;
        this.host = host;
    }
    

    /**
     * Opens network interface for listening packets.
     * @return is network interface successfully opened.
     * @throws HostException host exception
     * @throws NetworkInterfaceException networkInterface exception
     */
    public boolean openNetworkInterface() throws HostException, NetworkInterfaceException {
        if (networkInterface == null) {
            networkInterface = findNetworkInterfaceByIp();
        }
        if (networkInterface != null) {
            try {
                captor = JpcapCaptor
                        .openDevice(networkInterface, MAX_BYTES_TRANSFERED_AT_ONCE, IS_PROMISCUOUS, TIMEOUT);
                captor.setFilter("dst net " + getHostIp(host) + " && port " + host.getIPAddress().getPort(), false);
                return true;
            } catch (IOException e) {
                LOG.error(NETWORK_INTERFACE_NOT_OPENED_EXCEPTION, e);
                throw new NetworkInterfaceException(NETWORK_INTERFACE_NOT_OPENED_EXCEPTION, e);
            }
        }
        return false;
    }
    
    /**
     * Returns sniffed packet.
     * @return packet
     */
    public ModulePacket getPacket() {
        return packetsStorage.getPacket();
    }
    
    /**
     * Returns host index.
     * @return host index
     */
    public HostIndex getHostIndex() {
        assert host != null;
        return host.getHostIndex();
    }
    
    @Override
    public void run() {
        if (captor != null) {
            captor.loopPacket(-1, packetsStorage);
        }
    }
    
    private String getHostIp(final HostInformation host) {
        final String ipAddress = host.getIPAddress().toString();
        final int index = ipAddress.indexOf(':');
        if (index == -1) {
            return getIp(ipAddress);
        } else {
            return getIp(ipAddress.substring(0, index));
        }
    }
    
    private String getIp(final String ipAddress) {
        return ipAddress.replaceAll("/", "");
    }
    
    /**
     * Finds network interface containing host.
     * @return network interface
     * @throws HostException host exception
     * @throws NetworkInterfaceException network interface exception
     */
    public NetworkInterface findNetworkInterfaceByIp() throws HostException, NetworkInterfaceException {
        assert host != null;
        
        if (host.getIPAddress() == null) {
            LOG.error("Host without ip address");
            throw new HostException("Host without ip address");
        }
        
        final NetworkInterface[] devices = JpcapCaptor.getDeviceList();
        for (int i = 0; i < devices.length; i++) {
            for (NetworkInterfaceAddress ni : devices[i].addresses) {
                if (ni.address != null && getIp(ni.address.toString()).compareToIgnoreCase(getHostIp(host)) == 0) {
                    return devices[i];
                }
            }
        }
        LOG.error(NETWORK_INTERFACE_NOT_FOUND_EXCEPTION);
        throw new NetworkInterfaceException(NETWORK_INTERFACE_NOT_FOUND_EXCEPTION);
    }
}
