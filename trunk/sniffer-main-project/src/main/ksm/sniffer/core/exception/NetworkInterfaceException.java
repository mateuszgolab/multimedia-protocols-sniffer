package ksm.sniffer.core.exception;

/**
 * Represents exception class related to network interface.
 */
@SuppressWarnings("serial")
public class NetworkInterfaceException extends Exception {
    
    /**
     * Constructor with message.
     * @param message exception message
     */
    public NetworkInterfaceException(final String message) {
        super(message);
    }
    
    /**
     * Constructor .
     */
    public NetworkInterfaceException() {
        super();
    }
    
    /**
     * Constructor with message and cause.
     * @param message exception message
     * @param exception catched exception object
     */
    public NetworkInterfaceException(final String message, final Exception exception) {
        super(message, exception.getCause());
    }
    
    
}
