package ksm.sniffer.module.api.net;

/**
 * Represents IP address.
 */
public interface IPAddress {
    
    /**
     * Returns IP address numbers in table.
     * @return IP address numbers in table (cannot be {@code null}).
     */
    int[] getIPInTable();
    
    /**
     * Returns host connected port.
     * @return host connected port.
     */
    int getPort();
    
    /**
     * Returns string representation of IP address ({@code IP_ADDRESS:PORT}).
     * @return string representation of IP address (cannot be {@code null}).
     */
    String toString();
}
