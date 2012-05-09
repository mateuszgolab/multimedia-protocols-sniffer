package ksm.sniffer.gui.composite;

// CHECKSTYLE:OFF MagicNumber

import java.awt.Color;
import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import ksm.sniffer.gui.resources.Messages;

/**
 * Class represents component with port number field.
 */
@SuppressWarnings("serial")
public class PortFormattedField extends AbstractHostInfoFormattedField<Integer> {
    
    /**
     * Maximal port value.
     */
    private static final int MAX_PORT = 65535;
    /**
     * Minimal port value.
     */
    private static final int MIN_PORT = 0;
    
    /**
     * Constructs component with port number field.
     */
    public PortFormattedField() {
        super(Messages.getString("error.port"));
    }
    
    
    @Override
    public MaskFormatter getMaskFormatter() {
        try {
            return new MaskFormatter("#####");
        } catch (ParseException e) {
            LOG.error("Problem with port mask formatter", e);
        }
        return null;
    }
    
    @Override
    public Integer getFormattedValue() {
        getPopup().hidePopup();
        final String text = super.getText().trim().replaceAll(" ", "");
        try {
            final int port = Integer.parseInt(text);
            if (port >= MIN_PORT && port <= MAX_PORT) {
                return port;
            } else {
                this.setForeground(Color.RED);
                getPopup().showPopup();
            }
        } catch (NumberFormatException e) {
            this.setForeground(Color.RED);
            getPopup().showPopup();
        }
        
        return null;
    }
}
