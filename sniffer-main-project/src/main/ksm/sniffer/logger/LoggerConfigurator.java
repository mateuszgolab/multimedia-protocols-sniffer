package ksm.sniffer.logger;

import java.net.URL;

import org.apache.log4j.PropertyConfigurator;

/**
 * Responsible for logger configuration.
 */
public final class LoggerConfigurator {
    
    private static final URL DEFAULT_PROPERTIES_URL = LoggerConfigurator.class.getResource("log4j.properties");
    
    private LoggerConfigurator() {
    }
    
    /**
     * Configure logger with default properties.
     */
    public static void configure() {
        configure(DEFAULT_PROPERTIES_URL);
    }
    
    /**
     * Configure logger.
     * @param config URL to configuration file (cannot be {@code null}).
     */
    public static void configure(final URL config) {
        assert config != null : "URL to configuration file cannot be null";
        
        PropertyConfigurator.configure(config);
    }
    
}
