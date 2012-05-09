package ksm.sniffer.core.net;

import ksm.sniffer.module.api.net.IPAddress;

/**
 * Class that implements IPAddress interface.
 */
public class IPAddressImpl implements IPAddress {
    
    private final String ipAddress;
    private final int port;
    
    /**
     * Constructs class with ipAddress.
     * @param ipAddress IP address
     * @param port port
     */
    public IPAddressImpl(final String ipAddress, final int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }
    
    @Override
    public int[] getIPInTable() {
        final String[] numbers = ipAddress.split("\\.");
        final int[] ipNumbers = new int[4]; // CHECKSTYLE:LINES_0 MagicNumber
        for (int i = 0; i < ipNumbers.length && i < numbers.length; ++i) {
            ipNumbers[i] = new Integer(numbers[i]);
        }
        
        return ipNumbers;
    }
    
    @Override
    public String toString() {
        return ipAddress + ':' + port;
    }
    
    @Override
    public int getPort() {
        return port;
    }
}
