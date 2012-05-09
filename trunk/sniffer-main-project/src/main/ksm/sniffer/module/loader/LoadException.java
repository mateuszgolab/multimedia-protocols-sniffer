package ksm.sniffer.module.loader;

/**
 * Exception thrown if class instantiating goes wrong.
 */
@SuppressWarnings("serial")
public class LoadException extends Exception {
    
    private final String moduleClass;
    
    /**
     * Default constructor.
     */
    public LoadException() {
        this("");
    }
    
    /**
     * Constructor.
     * @param cause cause
     */
    public LoadException(final Exception cause) {
        this(cause, "");
    }
    
    /**
     * Constructor.
     * @param cause cause
     * @param moduleClass module class name
     */
    public LoadException(final Exception cause, final String moduleClass) {
        super(cause);
        this.moduleClass = moduleClass;
    }
    
    /**
     * Constructor.
     * @param moduleClass module class name
     */
    public LoadException(final String moduleClass) {
        this.moduleClass = moduleClass;
    }
    
    /**
     * Getter.
     * @return the module class name or empty string if module class name hasn't been set.
     */
    public String getModuleClass() {
        return moduleClass;
    }
}
