package ksm.sniffer.core.exception;

/**
 * Represents exception class related to module.
 */
@SuppressWarnings("serial")
public class HostException extends Exception {
    
    /**
     * Constructor with message.
     * @param message exception message
     */
    public HostException(final String message) {
        super(message);
    }
    
    /**
     * Constructor .
     */
    public HostException() {
        super();
    }
    
    /**
     * Constructor with message and cause.
     * @param message exception message
     * @param exception catched exception object
     */
    public HostException(final String message, final Exception exception) {
        super(message, exception.getCause());
    }
    
    
}
