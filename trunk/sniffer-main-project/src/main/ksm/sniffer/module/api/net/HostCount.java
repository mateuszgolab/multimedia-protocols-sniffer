package ksm.sniffer.module.api.net;

/**
 * Represents available intercepted hosts number.
 */
public enum HostCount {
    /**
     * One.
     */
    ONE(1),
    /**
     * Two.
     */
    TWO(2);
    
    private final Integer count;
    
    private HostCount(final int count) {
        this.count = count;
    }
    
    /**
     * Returns {@code int} representation of {@code this}.
     * @return hosts count.
     */
    public int toInt() {
        return count.intValue();
    }
    
    /**
     * Returns {@link Integer} representation of {@code this}.
     * @return hosts count.
     */
    public Integer toInteger() {
        return count;
    }
}
