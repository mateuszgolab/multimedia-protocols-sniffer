package ksm.sniffer.core.packet;

import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;
import ksm.sniffer.module.api.net.packet.Packet;
import ksm.sniffer.module.api.net.packet.Timestamp;

/**
 * Class converting Jpcap packet data to Module packet data.
 */
@SuppressWarnings("restriction")
public class ModulePacket implements Packet {
    
    private static final String COLON = ":";
    private final jpcap.packet.Packet sniffedPacket;
    private final ModuleTimestamp timestamp;
    
    /**
     * Creates ModulePacket from JPcap Packet.
     * @param packet sniffed packet
     */
    public ModulePacket(final jpcap.packet.Packet packet) {
        sniffedPacket = packet;
        timestamp = packet != null ? new ModuleTimestamp(packet.sec, packet.usec) : new ModuleTimestamp();
    }
    
    @Override
    public byte[] getHeader() {
        return sniffedPacket.header;
    }
    
    @Override
    public byte[] getData() {
        return sniffedPacket.data;
    }
    
    @Override
    public int getLength() {
        return sniffedPacket.len;
    }
    
    @Override
    public int getCapturedLength() {
        return sniffedPacket.caplen;
    }
    
    @Override
    public Timestamp getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String toString() {
        if (sniffedPacket instanceof UDPPacket) {
            final UDPPacket udp = (UDPPacket) sniffedPacket;
            return "UDP " + udp.src_ip + COLON + udp.src_port + " " + udp.dst_ip + COLON + udp.dst_port;
        }
        
        if (sniffedPacket instanceof TCPPacket) {
            final TCPPacket tcp = (TCPPacket) sniffedPacket;
            return "TCP " + tcp.src_ip + COLON + tcp.src_port + " " + tcp.dst_ip + COLON + tcp.dst_port;
        }
        return "";
    }
}
