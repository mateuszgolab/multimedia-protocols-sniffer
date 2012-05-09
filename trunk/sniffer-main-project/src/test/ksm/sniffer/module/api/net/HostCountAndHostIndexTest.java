package ksm.sniffer.module.api.net;

import org.junit.Assert;
import org.junit.Test;

public final class HostCountAndHostIndexTest {
    
    @Test
    public void theSameValuesCount() {
        final String message = "Enums should have the same count of values (host count: " + HostCount.values().length
                + ", host index: " + HostIndex.values().length + ')';
        Assert.assertEquals(message, HostCount.values().length, HostIndex.values().length);
    }
    
    @Test
    public void correctIntegerValues() {
        for (HostCount hostCount : HostCount.values()) {
            final int index = hostCount.toInt() - 1;
            boolean correct = false;
            for (HostIndex hostIndex : HostIndex.values()) {
                if (hostIndex.toInt() == index) {
                    correct = true;
                    break;
                }
            }
            Assert.assertTrue("Cannot find host index (" + index + ") for host count (" + hostCount.toString() + ')',
                    correct);
        }
    }
    
    @Test
    public void correctHostCountValues() {
        final boolean[] setValues = new boolean[HostCount.values().length];
        for (HostCount hostCount : HostCount.values()) {
            setValues[hostCount.toInt() - 1] = true;
        }
        for (int i = 0; i < setValues.length; ++i) {
            if (!setValues[i]) {
                Assert.fail("Host count hasn't specified count: " + (i + 1));
            }
        }
    }
    
    @Test
    public void correctHostIndexValues() {
        final boolean[] setValues = new boolean[HostIndex.values().length];
        for (HostIndex hostIndex : HostIndex.values()) {
            setValues[hostIndex.toInt()] = true;
        }
        for (int i = 0; i < setValues.length; ++i) {
            if (!setValues[i]) {
                Assert.fail("Host index hasn't specified index: " + i);
            }
        }
    }
}
