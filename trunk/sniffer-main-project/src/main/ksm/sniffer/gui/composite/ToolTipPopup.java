package ksm.sniffer.gui.composite;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

/**
 * Popup with information displayed on component.
 */
public class ToolTipPopup {
    
    private Popup popup;
    private final Component component;
    private final JLabel toolTip;
    
    /**
     * Constructs object.
     * @param component component where popup should be displayed
     * @param toolTipText tooltip text
     */
    public ToolTipPopup(final Component component, final String toolTipText) {
        this.component = component;
        toolTip = new JLabel();
        toolTip.setBackground(Color.ORANGE);
        toolTip.setText(toolTipText);
        toolTip.setOpaque(true);
        
    }
    
    /**
     * Shows popup.
     */
    public void showPopup() {
        hidePopup();
        // CHECKSTYLE:LINES_2 MagicNumber
        popup = PopupFactory.getSharedInstance().getPopup(component, toolTip, component.getLocationOnScreen().x,
                component.getLocationOnScreen().y - 10);
        if (popup != null) {
            popup.show();
        }
    }
    
    /**
     * Hides popup.
     */
    public void hidePopup() {
        if (popup != null) {
            popup.hide();
            // If there is no null assign there are popup problems
            popup = null; // NOPMD
        }
    }
}
