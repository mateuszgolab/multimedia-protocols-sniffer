package ksm.sniffer.module.api.net;

/**
 * Represents host information.
 */
public interface HostInformation {
    
    /**
     * Returns host IP address.
     * @return host IP address (cannot be {@code null}).
     */
    IPAddress getIPAddress();
    
    /**
     * Returns net interface name.
     * @return net interface name (cannot be {@code null}).
     */
    String getInterfaceName();
    
    /**
     * Returns host index.
     * @return host index (cannot be {@code null}).
     */
    HostIndex getHostIndex();
}
