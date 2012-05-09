package ksm.sniffer.core.net;

import ksm.sniffer.module.api.net.HostIndex;
import ksm.sniffer.module.api.net.HostInformation;
import ksm.sniffer.module.api.net.IPAddress;

/**
 * Class that implements HostInformation interface.
 */
public class HostInformationImpl implements HostInformation {
    
    private final IPAddress ipAddress;
    private final HostIndex hostIndex;
    private final String interfaceName;
    
    /**
     * Constructs HostInformation with given values.
     * @param ipAddress instance of IPAddress
     * @param interfaceName interface name
     * @param hostIndex host index
     */
    public HostInformationImpl(final IPAddress ipAddress, final String interfaceName, final HostIndex hostIndex) {
        this.ipAddress = ipAddress;
        this.interfaceName = interfaceName;
        this.hostIndex = hostIndex;
    }
    
    @Override
    public HostIndex getHostIndex() {
        return hostIndex;
    }
    
    @Override
    public String getInterfaceName() {
        return interfaceName;
    }
    
    @Override
    public IPAddress getIPAddress() {
        return ipAddress;
    }
}
