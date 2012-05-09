package ksm.sniffer.core.packet;

import ksm.sniffer.module.api.net.packet.Timestamp;

/**
 * Class converting common timestamps to Module TImestamp.
 */
public class ModuleTimestamp implements Timestamp {
    
    private final long sec;
    private final long usec;
    
    /**
     * Creates Timestamp.
     * @param sec seconds
     * @param usec microseconds
     */
    public ModuleTimestamp(final long sec, final long usec) {
        this.sec = sec;
        this.usec = usec;
    }
    
    /**
     * Creates default Timestamp with 0.0 sec.
     */
    public ModuleTimestamp() {
        this.sec = 0;
        this.usec = 0;
    }
    
    @Override
    public long getSeconds() {
        return sec;
    }
    
    @Override
    public long getMicroseconds() {
        return usec;
    }
}
