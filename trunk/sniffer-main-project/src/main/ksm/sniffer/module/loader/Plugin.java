package ksm.sniffer.module.loader;

/**
 * Represents a plugin.
 */
public class Plugin {
    
    private String className;
    
    /**
     * Constructor.
     * @param className main plugin class full name
     */
    public Plugin(final String className) {
        this.className = className;
    }
    
    /**
     * Getter.
     * @return className
     */
    public String getClassName() {
        return className;
    }
    
    /**
     * Setter.
     * @param className the class name
     */
    public void setClassName(final String className) {
        this.className = className;
    }
}
