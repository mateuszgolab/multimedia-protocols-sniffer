package ksm.sniffer.core.packet.receiver;

import java.util.ArrayList;
import java.util.List;

import jpcap.PacketReceiver;
import jpcap.packet.Packet;
import ksm.sniffer.core.packet.ModulePacket;

/**
 * Class responsible for receiving and converting packets from Jpcap to module version.
 */
@SuppressWarnings("restriction")
public class PacketsStorage implements PacketReceiver {
    
    private final List<ModulePacket> packets = new ArrayList<ModulePacket>();
    
    @Override
    public void receivePacket(final Packet packet) {
        packets.add(new ModulePacket(packet));
    }
    
    /**
     * Returns received packet in order as packets came.
     * @return received packet
     */
    public ModulePacket getPacket() {
        return packets.isEmpty() ? null : packets.remove(0); // NOPMD: assigning an object to null
    }
}
