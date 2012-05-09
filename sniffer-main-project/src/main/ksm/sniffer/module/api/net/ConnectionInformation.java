package ksm.sniffer.module.api.net;

/**
 * Represents module net connection information.
 */
public interface ConnectionInformation {
    
    /**
     * Returns net connection protocol type.
     * @return net connection protocol type (cannot be {@code null}).
     */
    Protocol getProtocol();
    
    /**
     * Returns available intercepted hosts number.
     * @return available intercepted hosts number (cannot be {@code null}).
     */
    HostCount getHostCount();
}
