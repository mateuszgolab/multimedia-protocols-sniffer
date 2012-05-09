package ksm.sniffer.module.api.net.packet;

/**
 * Represents timestamp.
 */
public interface Timestamp {
    
    /**
     * Returns timestamp in seconds.
     * @return timestamp in seconds.
     */
    long getSeconds();
    
    /**
     * Returns timestamp in microseconds.
     * @return timestamp in microseconds.
     */
    long getMicroseconds();
}
