package ksm.sniffer.core.net;

// CHECKSTYLE:OFF MagicNumber

import ksm.sniffer.module.api.net.IPAddress;

import org.junit.Assert;
import org.junit.Test;

public final class IPAddressTest {
    
    @Test
    public void correctIpAddress() {
        final IPAddress ipAddress = new IPAddressImpl("100.0.123.255", 500); // NOPMD: hard coded IP address.
        
        final int[] address = ipAddress.getIPInTable();
        Assert.assertEquals(4, address.length);
        
        Assert.assertEquals(100, address[0]);
        Assert.assertEquals(0, address[1]);
        Assert.assertEquals(123, address[2]);
        Assert.assertEquals(255, address[3]);
    }
    
}
