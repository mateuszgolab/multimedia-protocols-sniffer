package ksm.sniffer.module.loader;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Loads properties.
 */
public final class PluginsConfiguration {
    
    private static final String BUNDLE_NAME = "ksm.sniffer.module.loader.conf"; //$NON-NLS-1$ 
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
    
    private PluginsConfiguration() {
    }
    
    /**
     * Gets string property.
     * @param key key
     * @return property
     */
    public static String getString(final String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
