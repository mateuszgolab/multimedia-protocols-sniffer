package ksm.sniffer.module.loader;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ksm.sniffer.module.api.Module;

/**
 * Modules' container.
 */
public class ModuleContainer {
    
    private final Map<String, Module> modulesMap = new HashMap<String, Module>();
    
    /**
     * Constructor.
     */
    ModuleContainer() {
        super();
    }
    
    /**
     * Adds a module to the container.
     * @param module module to add
     */
    void addModule(final Module module) {
        modulesMap.put(module.getName(), module);
    }
    
    /**
     * Gets module by its name.
     * @param moduleName module name
     * @return module
     */
    public Module getModule(final String moduleName) {
        return modulesMap.get(moduleName);
    }
    
    /**
     * A collection of all modules.
     * @return all modules
     */
    public Collection<Module> getAllModules() {
        return modulesMap.values();
    }
    
    /**
     * Clears the container.
     */
    void clear() {
        modulesMap.clear();
    }
}
