package ksm.sniffer.core.exception;

/**
 * Represents exception class related to module.
 */
@SuppressWarnings("serial")
public class ModuleException extends Exception {
    
    /**
     * Constructor with message.
     * @param message exception message
     */
    public ModuleException(final String message) {
        super(message);
    }
    
    /**
     * Constructor .
     */
    public ModuleException() {
        super();
    }
    
    /**
     * Constructor with message and cause.
     * @param message exception message
     * @param exception catched exception object
     */
    public ModuleException(final String message, final Exception exception) {
        super(message, exception.getCause());
    }
    
    
}
