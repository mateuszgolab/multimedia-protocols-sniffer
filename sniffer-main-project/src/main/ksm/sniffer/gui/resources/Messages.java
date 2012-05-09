package ksm.sniffer.gui.resources;

import java.beans.Beans;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Class used to get massages from resources.
 */
public final class Messages {
    
    private static final String BUNDLE_NAME = "ksm.sniffer.gui.resources.messages"; //$NON-NLS-1$
    private static final ResourceBundle RESOURCE_BUNDLE = loadBundle();
    
    private Messages() {
    }
    
    private static ResourceBundle loadBundle() {
        return ResourceBundle.getBundle(BUNDLE_NAME);
    }
    
    /**
     * Strings access.
     * @param key properties key
     * @return properties value
     */
    public static String getString(final String key) {
        try {
            final ResourceBundle bundle = Beans.isDesignTime() ? loadBundle() : RESOURCE_BUNDLE;
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
