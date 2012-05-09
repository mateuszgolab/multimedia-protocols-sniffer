package ksm.sniffer.module.loader;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import ksm.sniffer.module.api.Module;

import org.apache.log4j.Logger;

/**
 * Loads classes from .jar files.
 */
public class PluginClassLoader {
    
    private static final Logger LOG = Logger.getLogger(PluginClassLoader.class);
    private ClassLoader classLoader;
    
    /**
     * Creates PluginClassLoader which loads classes from directory.
     * @param directory where to load from
     * @throws LoadException load exception
     */
    public PluginClassLoader(final String directory) throws LoadException {
        final File dir = new File(directory);
        String[] jarFiles = dir.list(new FilenameFilter() {
            
            @Override
            public boolean accept(final File dir, final String name) {
                return name.endsWith(".jar");
            }
        });
        try {
            classLoader = URLClassLoader.newInstance(getUrls(jarFiles, dir), getClass().getClassLoader());
        } catch (Exception e) {
            throw new LoadException(e);
        }
    }
    
    /**
     * Loads module class and returns an instance of it.
     * @param plugin plugin
     * @return instance of a module
     * @throws LoadException class instantiating went wrong
     */
    public Module getModule(final Plugin plugin) throws LoadException {
        try {
            final Class<?> pluginClass = classLoader.loadClass(plugin.getClassName()); // Class.forName(plugin.getClassName(),
                                                                                       // true, classLoader);
            final Class<? extends Module> runClass = pluginClass.asSubclass(Module.class);
            final Constructor<? extends Module> constructor = runClass.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new LoadException(e, plugin.getClassName());
        }
    }
    
    private URL[] getUrls(final String[] strings, final File directory) throws MalformedURLException {
        final URL[] urls = new URL[strings.length];
        for (int i = 0; i < strings.length; i++) {
            urls[i] = new File(directory, strings[i]).toURI().toURL();
        }
        return urls;
    }
}
