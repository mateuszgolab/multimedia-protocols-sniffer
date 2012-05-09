package ksm.sniffer.gui.protocol;

/**
 * Protocol clicked listener.
 */
public interface ProtocolChooseListener {
    
    /**
     * Invokes when protocol is selected.
     * @param protocolNumber protocol number
     */
    void protocolChoosed(final int protocolNumber);
}
