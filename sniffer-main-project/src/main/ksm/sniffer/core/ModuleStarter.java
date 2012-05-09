package ksm.sniffer.core;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;
import ksm.sniffer.core.exception.HostException;
import ksm.sniffer.core.exception.ModuleException;
import ksm.sniffer.core.exception.NetworkInterfaceException;
import ksm.sniffer.core.net.NetworkInterfaceName;
import ksm.sniffer.module.api.Module;
import ksm.sniffer.module.api.controller.ModuleController;
import ksm.sniffer.module.api.net.HostInformation;

import org.apache.log4j.Logger;

// CHECKSTYLE:OFF ClassDataAbstractionCoupling

/**
 * Class responsible for launching sniffer for selected module, and selected module as well.
 */
@SuppressWarnings("restriction")
public final class ModuleStarter {
    
    private static final Logger LOG = Logger.getLogger(ModuleStarter.class);
    private final List<Thread> threads = new ArrayList<Thread>();
    
    private ModuleStarter() {
    }
    
    /**
     * Creates sniffer thread for each host.
     * @param hostsInformation information about the host.
     * @param moduleController module controller of selected module.
     * @throws NetworkInterfaceException
     * @throws HostException
     */
    private void createThreads(final List<HostInformation> hostsInformation, final ModuleController moduleController)
            throws HostException, NetworkInterfaceException {

        threads.clear();
        
        if (hostsInformation.size() == 1) {
            for (HostInformation hi : hostsInformation) {
                final SnifferWorker sniffer = new SnifferWorker(hi, moduleController);
                sniffer.initialize();
                threads.add(new Thread(sniffer));
            }
        } else if (hostsInformation.size() > 1) {
            final NetworkInterface networkInterface = getFirstNetworkInterface(hostsInformation);
            for (HostInformation hi : hostsInformation) {
                final SnifferWorker sniffer = new SnifferWorker(hi, networkInterface, moduleController);
                sniffer.initialize();
                threads.add(new Thread(sniffer));
            }
        }
    }
    
    /**
     * Starts threads.
     */
    private void startThreads() {
        for (Thread t : threads) {
            t.start();
        }
    }
    
    
    /**
     * Starts new module and his sniffer.
     * @param hostsInformation host information
     * @param module selecred module to sniff
     * @throws ModuleException module exception
     * @throws NetworkInterfaceException network exception
     * @throws HostException host exception
     */
    public static void startModule(final List<HostInformation> hostsInformation, final Module module)
            throws ModuleException, HostException, NetworkInterfaceException {
        
        assert module != null;
        
        try {
            final ModuleController moduleController = module.newController();
            final ModuleStarter moduleStarter = new ModuleStarter();
            
            if (moduleController == null) {
                LOG.error("Module Controller could not be created");
                throw new ModuleException("Module Controller could not be created");
            }
            
            moduleStarter.createThreads(hostsInformation, moduleController);
            moduleController.setHostsInformation(hostsInformation);
            moduleController.start();
            moduleStarter.startThreads();
        } catch (RuntimeException e) {
            LOG.error("Module not started", e);
            throw new ModuleException("Module not started", e);
        }
    }
    
    /**
     * Returns latest map of network interfaces and their hosts addresses.
     * @return map with data
     */
    public static Map<NetworkInterfaceName, List<String>> getNetworkInterfaces() {
        final Map<NetworkInterfaceName, List<String>> networkInterfaces = new HashMap<NetworkInterfaceName, List<String>>();
        final NetworkInterface[] devices = JpcapCaptor.getDeviceList();
        for (int i = 0; i < devices.length; i++) {
            final List<String> addresses = new ArrayList<String>();
            for (NetworkInterfaceAddress ni : devices[i].addresses) {
                if (!isIPv6address(ni.address)) {
                    addresses.add(ni.address.toString());
                }
            }
            networkInterfaces.put(new NetworkInterfaceName(devices[i].description), addresses);
        }
        return networkInterfaces;
    }
    
    private static boolean isIPv6address(final InetAddress address) {
        return address instanceof Inet6Address;
    }
    
    private NetworkInterface getFirstNetworkInterface(final List<HostInformation> hostsInformation)
            throws NetworkInterfaceException {
        for (HostInformation hi : hostsInformation) {
            final HostSniffer tmpHostSniffer = new HostSniffer(hi);
            try {
                return tmpHostSniffer.findNetworkInterfaceByIp();
            } catch (Exception e) {
                continue;
            }
        }
        
        LOG.error(HostSniffer.NETWORK_INTERFACE_NOT_FOUND_EXCEPTION);
        throw new NetworkInterfaceException(HostSniffer.NETWORK_INTERFACE_NOT_FOUND_EXCEPTION);
    }

}
