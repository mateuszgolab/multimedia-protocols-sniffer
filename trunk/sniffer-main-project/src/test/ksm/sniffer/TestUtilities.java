package ksm.sniffer;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ksm.sniffer.core.ModuleStarter;
import ksm.sniffer.core.net.NetworkInterfaceName;


public final class TestUtilities {
    
    private TestUtilities() {
        
    }
    
    public static NetworkInterfaceName getExistingNetworkInterface() {
        final Map<NetworkInterfaceName, List<String>> networkInterfaces = ModuleStarter.getNetworkInterfaces();
        NetworkInterfaceName name = new NetworkInterfaceName("");
        final Iterator<NetworkInterfaceName> mapIterator = networkInterfaces.keySet().iterator();
        
        while (mapIterator.hasNext()) {
            name = mapIterator.next();
            if (!networkInterfaces.get(name).isEmpty()) {
                break;
            }
        }
        
        return name;
    }
    
    public static String getNetworkInterfaceHostIpAddress(final NetworkInterfaceName networkInterfaceName) {
        final Map<NetworkInterfaceName, List<String>> networkInterfaces = ModuleStarter.getNetworkInterfaces();
        for (NetworkInterfaceName name : networkInterfaces.keySet()) {
            if (name.toString().compareToIgnoreCase(networkInterfaceName.toString()) == 0
                    && networkInterfaces.get(networkInterfaceName) != null) {
                return networkInterfaces.get(networkInterfaceName).iterator().next();
            }
        }
        return "/0.0.0.0";
    }
    
}
