package ksm.sniffer.module.loader;

/**
 * Plugins information file parser.
 */
public interface DescriptorParser {
    
    /**
     * Gets next plugin class name.
     * @return plugin class name
     */
    String getNextPluginClassInfo();
    
}
