package ksm.sniffer.gui.host;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that manages start button click events.
 */
public final class StartManager {
    
    private static List<StartListener> listeners = new ArrayList<StartListener>();
    
    private StartManager() {
    }
    
    /**
     * Adds new listener.
     * @param litener listener to add.
     */
    public static void addListener(final StartListener litener) {
        listeners.add(litener);
    }
    
    /**
     * Removes listener.
     * @param listener listener to remove
     */
    public static void removeListener(final StartListener listener) {
        listeners.remove(listener);
    }
    
    /**
     * Informs listeners about start event.
     */
    public static void start() {
        for (StartListener listener : listeners) {
            listener.start();
        }
    }
}
