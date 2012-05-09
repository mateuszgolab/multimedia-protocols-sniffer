package ksm.sniffer.core;

import jpcap.NetworkInterface;
import ksm.sniffer.core.exception.HostException;
import ksm.sniffer.core.exception.NetworkInterfaceException;
import ksm.sniffer.core.packet.ModulePacket;
import ksm.sniffer.module.api.controller.ModuleController;
import ksm.sniffer.module.api.net.HostInformation;

/**
 * Class responsible for Sniffing on the specified host and sending received packets to the specified module.
 */
@SuppressWarnings("restriction")
public class SnifferWorker implements Runnable {
    
    private static final int WAIT_TIME_IN_MILLISECONDS = 1;
    private static final int WAIT_FOR_MODULE_START_IN_MILLISECONDS = 200;
    private final HostSniffer hostSniffer;
    private final ModuleController module;
    
    /**
     * Creates HostSniffer.
     * @param hostInforamation host to sniff
     * @param module module
     */
    public SnifferWorker(final HostInformation hostInforamation, final ModuleController module) {
        this.module = module;
        this.hostSniffer = new HostSniffer(hostInforamation);
    }
    
    /**
     * Creates HostSniffer.
     * @param hostInforamation host to sniff
     * @param module module
     * @param networkInterface network interface
     */
    public SnifferWorker(final HostInformation hostInforamation, final NetworkInterface networkInterface,
            final ModuleController module) {
        this.module = module;
        this.hostSniffer = new HostSniffer(hostInforamation, networkInterface);
    }
    
    /**
     * Opens network interface and starts sniffing host.
     * @throws HostException host exception
     * @throws NetworkInterfaceException network interface exception
     */
    public void initialize() throws HostException, NetworkInterfaceException {
        if (this.hostSniffer.openNetworkInterface()) {
            final Thread thread = new Thread(hostSniffer);
            thread.start();
        }
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                switch (module.getModuleState()) {
                    case STARTING:
                        Thread.sleep(WAIT_FOR_MODULE_START_IN_MILLISECONDS);
                        break;
                    case RUNNING:
                        final ModulePacket packet = hostSniffer.getPacket();
                        if (packet != null) {
                            module.receiveData(packet, hostSniffer.getHostIndex());
                            Thread.sleep(WAIT_TIME_IN_MILLISECONDS);
                        }
                        break;
                    case STOPPED:
                        return;
                    default:
                }
            }
        } catch (InterruptedException e) {
            return;
        }
    }
}
