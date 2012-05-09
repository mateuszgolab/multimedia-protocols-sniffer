package ksm.sniffer.module.api.net.packet;

/**
 * Represents packet.
 */
public interface Packet {
    
    /**
     * Returns packet header.
     * @return packet header (cannot be {@code null}).
     */
    byte[] getHeader();
    
    /**
     * Returns packet data.
     * @return packet data (cannot be {@code null}).
     */
    byte[] getData();
    
    /**
     * Returns packet length.
     * @return packet length (cannot be less than {@code 0}).
     */
    int getLength();
    
    /**
     * Returns captured packet length.
     * @return captured packet length (cannot be less than {@code 0}).
     */
    int getCapturedLength();
    
    /**
     * Returns packet timestamp.
     * @return packet timestamp (cannot be {@code null}).
     */
    Timestamp getTimestamp();
}
