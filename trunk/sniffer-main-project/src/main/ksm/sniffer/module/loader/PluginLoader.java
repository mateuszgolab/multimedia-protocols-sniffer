package ksm.sniffer.module.loader;

import ksm.sniffer.module.api.Module;

import org.apache.log4j.Logger;

/**
 * Plugins' loader. A singleton.
 */
public final class PluginLoader {
    
    private static final Logger LOG = Logger.getLogger(PluginLoader.class);
    private static final String JAR_DIRECTORY = PluginsConfiguration.getString("PluginLoader.directory.jar");
    private static final String PLUGIN_XML_DIRECTORY = PluginsConfiguration
            .getString("PluginLoader.directory.pluginsxml");
    private static PluginLoader instance;
    private static final Object MUTEX = new Object();
    
    private ModuleContainer container;
    private final DescriptorParser parser;
    private final PluginClassLoader pluginClassLoader;
    
    private PluginLoader(final DescriptorParser parser, final PluginClassLoader pluginClassLoader) {
        this.parser = parser;
        this.pluginClassLoader = pluginClassLoader;
    }
    
    /**
     * Loads plugins if the container is empty.
     * Otherwise just returns the container.
     * @return container
     * @throws LoadException load exception
     */
    public ModuleContainer getModuleContainer() throws LoadException {
        return getModuleContainer(container == null);
    }
    
    /**
     * Reloads plugins if the reload is true. Returns the container.
     * @param reload if true then plugins are reloaded
     * @return container
     * @throws LoadException load exception
     */
    public ModuleContainer getModuleContainer(final boolean reload) throws LoadException {
        if (reload) {
            container = createNewContainer();
        }
        return container;
    }
    
    private ModuleContainer createNewContainer() throws LoadException {
        final ModuleContainer tempContainer = new ModuleContainer();
        loadPlugins(tempContainer);
        return tempContainer;
    }
    
    private Module getModule(final Plugin plugin) throws LoadException {
        LOG.info("Dodawanie pluginu: " + plugin.getClassName());
        return pluginClassLoader.getModule(plugin);
    }
    
    private void loadPlugins(final ModuleContainer container) throws LoadException {
        for (Plugin plugin = getNextPlugin(); plugin != null; plugin = getNextPlugin()) {
            container.addModule(getModule(plugin));
        }
    }
    
    private Plugin getNextPlugin() {
        final String className = parser.getNextPluginClassInfo();
        if (className != null) {
            return new Plugin(className);
        }
        return null;
    }
    
    /**
     * Getter.
     * @return instance
     * @throws LoadException load exception
     */
    public static PluginLoader getInstance() throws LoadException {
        synchronized (MUTEX) {
            if (instance == null) {
                instance = new PluginLoader(new XmlPluginInfoParser(PLUGIN_XML_DIRECTORY), new PluginClassLoader(
                        JAR_DIRECTORY));
            }
        }
        return instance;
    }
}
