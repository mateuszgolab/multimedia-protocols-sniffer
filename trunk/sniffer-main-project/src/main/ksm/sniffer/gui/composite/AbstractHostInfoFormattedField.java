package ksm.sniffer.gui.composite;

// CHECKSTYLE:OFF MagicNumber

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFormattedTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.MaskFormatter;

import org.apache.log4j.Logger;


/**
 * Class represents formatted field with popup.
 * @param <T> getValue method return type
 */
@SuppressWarnings("serial")
public abstract class AbstractHostInfoFormattedField<T> extends JFormattedTextField {
    
    /**
     * Logger.
     */
    protected static final Logger LOG = Logger.getLogger(AbstractHostInfoFormattedField.class);
    /**
     * Popup.
     */
    private ToolTipPopup popup;
    
    /**
     * Construct component.
     * @param errorMessage error message
     */
    public AbstractHostInfoFormattedField(final String errorMessage) {
        initialize(errorMessage);
    }
    
    /**
     * Initialize GUI components.
     */
    private void initialize(final String errorMessage) {
        this.setFormatter(getMaskFormatter());
        this.setFont(new Font("Courier", Font.PLAIN, 12));        
        popup = new ToolTipPopup(this, errorMessage);
        addCaretListener(new CaretListener() {
            
            @Override
            public void caretUpdate(final CaretEvent event) {
                if (getForeground().equals(Color.RED)) {
                    setForeground(Color.BLACK);
                    popup.hidePopup();
                }
            }
        });
        this.setText("");
        
    }
    
    /**
     * Field formatter.
     * @return formatter
     */
    public abstract MaskFormatter getMaskFormatter();
    
    /**
     * Gets form value.
     * @return value
     */
    public abstract T getFormattedValue();
    
    /**
     * Clear popup.
     */
    public void clear() {
        popup.hidePopup();
    }
    
    /**
     * Gets popup.
     * @return popup
     */
    public ToolTipPopup getPopup() {
        return popup;
    }
}
