package ksm.sniffer.module.api.net;

/**
 * Represents host index.
 */
public enum HostIndex {
    /**
     * First.
     */
    FIRST(0),
    /**
     * Second.
     */
    SECOND(1);
    
    private final Integer index;
    
    private HostIndex(final int index) {
        this.index = index;
    }
    
    /**
     * Returns {@code int} representation of {@code this}.
     * @return host index.
     */
    public int toInt() {
        return index.intValue();
    }
    
    /**
     * Returns {@link Integer} representation of {@code this}.
     * @return host index.
     */
    public Integer toInteger() {
        return index;
    }
}
