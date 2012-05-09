package ksm.sniffer.core.packet;

import jpcap.packet.Packet;

import org.junit.Assert;
import org.junit.Test;


@SuppressWarnings("restriction")
public final class ModulePacketTest {
    
    @Test
    public void correctTimeStamp() {
        ModulePacket modulePacket = new ModulePacket(new Packet());
        Assert.assertNotNull(modulePacket.getTimestamp());
        modulePacket = new ModulePacket(null);
        Assert.assertNotNull(modulePacket.getTimestamp());
    }
    
}
