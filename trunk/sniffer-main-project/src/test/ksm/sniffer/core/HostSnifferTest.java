package ksm.sniffer.core;

import ksm.sniffer.TestUtilities;
import ksm.sniffer.core.exception.HostException;
import ksm.sniffer.core.exception.NetworkInterfaceException;
import ksm.sniffer.core.net.HostInformationImpl;
import ksm.sniffer.core.net.IPAddressImpl;
import ksm.sniffer.core.net.NetworkInterfaceName;
import ksm.sniffer.module.api.net.HostIndex;
import ksm.sniffer.module.api.net.HostInformation;

import org.junit.Assert;
import org.junit.Test;


public final class HostSnifferTest {
    
    
    @Test
    public void correctAll() {
        final NetworkInterfaceName networkInterface = TestUtilities.getExistingNetworkInterface();
        final String ipAddress = TestUtilities.getNetworkInterfaceHostIpAddress(networkInterface);
        
        final HostInformation host = new HostInformationImpl(new IPAddressImpl(ipAddress, 5004),
                networkInterface.toString(), HostIndex.FIRST);
        final HostSniffer hostSniffer = new HostSniffer(host);
        try {
            hostSniffer.openNetworkInterface();
        } catch (HostException e) {
            Assert.fail(e.getMessage());
        } catch (NetworkInterfaceException e) {
            Assert.fail(e.getMessage());
        }
        
    }
    
    
    @Test
    public void correctIpAddress() {
        final NetworkInterfaceName networkInterface = TestUtilities.getExistingNetworkInterface();
        final String ipAddress = "/" + TestUtilities.getNetworkInterfaceHostIpAddress(networkInterface);
        
        final HostInformation host = new HostInformationImpl(new IPAddressImpl(ipAddress, 5004),
                networkInterface.toString(), HostIndex.FIRST);
        final HostSniffer hostSniffer = new HostSniffer(host);
        try {
            hostSniffer.openNetworkInterface();
        } catch (HostException e) {
            Assert.fail(e.getMessage());
        } catch (NetworkInterfaceException e) {
            Assert.fail(e.getMessage());
        }
        
    }
    
    @Test
    public void incorrectNetworkInterface() {
        final HostInformation host = new HostInformationImpl(new IPAddressImpl("test", 5004), "bla bla",
                HostIndex.FIRST);
        final HostSniffer hostSniffer = new HostSniffer(host);
        try {
            hostSniffer.openNetworkInterface();
        } catch (HostException e) {
            Assert.fail(e.getMessage());
        } catch (NetworkInterfaceException e) {
            Assert.assertEquals(e.getMessage(), HostSniffer.NETWORK_INTERFACE_NOT_FOUND_EXCEPTION, e.getMessage());
        }
    }
    
    
    @Test
    public void incorrectIpAddress() {
        final NetworkInterfaceName networkInterface = TestUtilities.getExistingNetworkInterface();
        final HostInformation host = new HostInformationImpl(new IPAddressImpl("wrongIp", 5004),
                networkInterface.toString(), HostIndex.FIRST);
        final HostSniffer hostSniffer = new HostSniffer(host);
        try {
            hostSniffer.openNetworkInterface();
        } catch (HostException e) {
            Assert.fail(e.getMessage());
        } catch (NetworkInterfaceException e) {
            Assert.assertEquals(e.getMessage(), HostSniffer.NETWORK_INTERFACE_NOT_FOUND_EXCEPTION, e.getMessage());
        }
    }
    
    @Test
    public void incorrectPort() {
        final NetworkInterfaceName networkInterface = TestUtilities.getExistingNetworkInterface();
        final String ipAddress = TestUtilities.getNetworkInterfaceHostIpAddress(networkInterface);
        final HostInformation host = new HostInformationImpl(new IPAddressImpl(ipAddress, -1),
                networkInterface.toString(), HostIndex.FIRST);
        final HostSniffer hostSniffer = new HostSniffer(host);
        try {
            hostSniffer.openNetworkInterface();
        } catch (HostException e) {
            Assert.fail(e.getMessage());
        } catch (NetworkInterfaceException e) {
            Assert.assertEquals(e.getMessage(), HostSniffer.NETWORK_INTERFACE_NOT_OPENED_EXCEPTION, e.getMessage());
        }
        
    }
    
}
