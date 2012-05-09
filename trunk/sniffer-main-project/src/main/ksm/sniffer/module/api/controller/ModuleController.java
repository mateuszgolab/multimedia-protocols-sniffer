package ksm.sniffer.module.api.controller;

import java.util.List;

import ksm.sniffer.module.api.net.HostIndex;
import ksm.sniffer.module.api.net.HostInformation;
import ksm.sniffer.module.api.net.packet.Packet;

/**
 * Represents module controller.
 */
public interface ModuleController {
    
    /**
     * Starts module.
     */
    void start();
    
    /**
     * Returns module's state.
     * @return module's state (cannot be {@code null}).
     */
    ModuleState getModuleState();
    
    /**
     * Sets intercepted hosts informations.
     * @param hosts list with intercepted hosts informations (cannot be {@code null} and empty).
     */
    void setHostsInformation(List<HostInformation> hosts);
    
    /**
     * Receives packet from host.
     * @param packet packet (cannot be {@code null}).
     * @param hostIndex index of host which send packet (cannot be bigger than maximal index of hosts list from
     * {@link #setHostsInformation(List)}).
     */
    void receiveData(Packet packet, HostIndex hostIndex);
}
