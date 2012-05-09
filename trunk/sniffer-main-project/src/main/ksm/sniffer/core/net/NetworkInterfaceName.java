package ksm.sniffer.core.net;


/**
 * Simplified NetworkInterface for GUI representations.
 */
public class NetworkInterfaceName {
    
    private final String name;
    
    /**
     * Contructor .
     * @param description description
     */
    public NetworkInterfaceName(final String description) {
        this.name = description;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}
