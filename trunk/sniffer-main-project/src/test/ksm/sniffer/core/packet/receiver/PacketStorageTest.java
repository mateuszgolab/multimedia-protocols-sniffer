package ksm.sniffer.core.packet.receiver;


import org.junit.Assert;
import org.junit.Test;


public final class PacketStorageTest {
    
    @SuppressWarnings("restriction")
    @Test
    public void packetOperations() {
        final PacketsStorage packetStorage = new PacketsStorage();
        Assert.assertNull(packetStorage.getPacket());
        packetStorage.receivePacket(null);
        Assert.assertNotNull(packetStorage.getPacket());
        packetStorage.receivePacket(new jpcap.packet.Packet());
        Assert.assertNotNull(packetStorage.getPacket());
        Assert.assertNull(packetStorage.getPacket());
    }
    
}
