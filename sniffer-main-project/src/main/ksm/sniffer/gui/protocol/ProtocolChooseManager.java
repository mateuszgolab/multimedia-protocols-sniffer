package ksm.sniffer.gui.protocol;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that manages protocol choose change.
 */
public final class ProtocolChooseManager {
    
    private static List<ProtocolChooseListener> listeners = new ArrayList<ProtocolChooseListener>();
    
    private ProtocolChooseManager() {
    }
    
    /**
     * invokes when protocol is selected.
     * @param protocolNumber protocol number
     */
    public static void protocolChoosed(final int protocolNumber) {
        for (ProtocolChooseListener litener : listeners) {
            litener.protocolChoosed(protocolNumber);
        }
    }
    
    /**
     * Adds listener.
     * @param litener listener to add
     */
    public static void addListener(final ProtocolChooseListener litener) {
        listeners.add(litener);
    }
    
    /**
     * Removes listener.
     * @param listener listener to remove
     */
    public static void removeListener(final ProtocolChooseListener listener) {
        listeners.remove(listener);
    }
    
    
}
