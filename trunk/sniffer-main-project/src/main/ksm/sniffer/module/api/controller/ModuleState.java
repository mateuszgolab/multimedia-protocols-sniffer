package ksm.sniffer.module.api.controller;

/**
 * Represents module state.
 */
public enum ModuleState {
    /**
     * Module state before call {@link ModuleController#start()}.
     */
    UNKNOW,
    /**
     * Module initializes GUI and listeners. Main module should wait till module state change to RUNNING.
     */
    STARTING,
    /**
     * Module is running. This is right moment to send packets.
     */
    RUNNING,
    /**
     * Module is stopped. User turned off module. Main module should stop module's thread.
     */
    STOPPED;
}
