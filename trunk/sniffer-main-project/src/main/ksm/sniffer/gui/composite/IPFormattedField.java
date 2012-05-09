package ksm.sniffer.gui.composite;

import java.awt.Color;
import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import ksm.sniffer.gui.resources.Messages;


/**
 * Class represents component with ip field.
 */
@SuppressWarnings("serial")
public class IPFormattedField extends AbstractHostInfoFormattedField<String> {
    private static final String IP_REGEX = "\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\"
            + ".(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";
    
    /**
     * Constructs panel.
     */
    public IPFormattedField() {
        super(Messages.getString("error.ip"));
    }
    
    @Override
    public MaskFormatter getMaskFormatter() {
        try {
            return new MaskFormatter("###.###.###.###");
        } catch (ParseException e) {
            LOG.error(e);
        }
        return null;
    }
    
    @Override
    public String getFormattedValue() {
        getPopup().hidePopup();
        final String text = super.getText().trim().replaceAll(" ", "");
        if (text.matches(IP_REGEX)) {
            return text;
        } else {
            getPopup().showPopup();
            this.setForeground(Color.RED);
        }
        return null;
    }
    
}
