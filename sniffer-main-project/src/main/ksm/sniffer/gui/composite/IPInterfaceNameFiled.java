package ksm.sniffer.gui.composite;

import java.awt.Component;

import ksm.sniffer.core.net.NetworkInterfaceName;

/**
 * IP fields interface.
 */
public interface IPInterfaceNameFiled {
    /**
     * Gets IPAddress.
     * @return ip address
     */
    String getIPAddres();
    
    /**
     * Gets interface name.
     * @return interface name
     */
    NetworkInterfaceName getInterfaceName();
    
    /**
     * Gets Interface name field.
     * @return interface field
     */
    Component getInterfaceNameComponent();
    
    /**
     * Gets Interface IP field.
     * @return ip field
     */
    Component getIPComponent();
    
    /**
     * Clears popups.
     */
    void clear();
}
