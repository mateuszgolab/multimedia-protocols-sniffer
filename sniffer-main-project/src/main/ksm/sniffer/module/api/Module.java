package ksm.sniffer.module.api;

import java.net.URL;

import ksm.sniffer.module.api.controller.ModuleController;
import ksm.sniffer.module.api.net.ConnectionInformation;

/**
 * Represents module.
 */
public interface Module {
    
    /**
     * Returns module name.
     * @return module name (cannot be {@code null}).
     */
    String getName();
    
    /**
     * Returns icon that represents module.
     * @return module icon (cannot be {@code null}).
     */
    URL getIconURL();
    
    /**
     * Returns module net connection information.
     * @return module net connection information (cannot be {@code null}).
     */
    ConnectionInformation getConnectionInformation();
    
    /**
     * Creates module controller.
     * @return module controller (cannot be {@code null}).
     */
    ModuleController newController();
}
