package ksm.sniffer.gui.protocol;

// CHECKSTYLE:OFF MagicNumber
// CHECKSTYLE:OFF ClassDataAbstractionCoupling
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import ksm.sniffer.gui.composite.TransparentBackgroundPanel;
import ksm.sniffer.gui.resources.IconsFactory;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * Panel that represents available protocol.
 */
@SuppressWarnings("serial")
public class ProtocolPanel extends TransparentBackgroundPanel implements ProtocolChooseListener {
    
    private JProgressBar activeBar;
    private final int protocolNumber;
    private JLabel showLbl;
    
    /**
     * Construct protocol panel.
     * @param protocolName protocol name
     * @param icon protocol icon
     * @param protocolNumber protocol number
     */
    public ProtocolPanel(final String protocolName, final ImageIcon icon, final int protocolNumber) {
        super(IconsFactory.getProtocolBackground());
        this.protocolNumber = protocolNumber;
        initialize(protocolName, icon, protocolNumber);
        
    }
    
    /**
     * Initialize GUI.
     * @param protocolName protocol name
     * @param icon protocol icon
     * @param protocolNumber protocol number
     */
    private void initialize(final String protocolName, final ImageIcon icon, final int protocolNumber) {
        setLayout(new FormLayout(new ColumnSpec[] {ColumnSpec.decode("2dlu"), FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("max(16dlu;default)"), ColumnSpec.decode("max(57dlu;default)"),
                FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("max(57dlu;default)")}, new RowSpec[] {FormFactory.NARROW_LINE_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC, FormFactory.NARROW_LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC}));
        final JLabel iconLbl = new JLabel("");
        if (icon != null) {
            // Resize icon to 16x16
            final Image newimg = icon.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
            iconLbl.setIcon(new ImageIcon(newimg));
        } else {
            iconLbl.setIcon(IconsFactory.getDefaultIcon());
        }
        add(iconLbl, "3, 2");
        
        final JLabel nameLbl = new JLabel("");
        nameLbl.setForeground(Color.WHITE);
        nameLbl.setFont(new Font("Arial", Font.BOLD, 12));
        add(nameLbl, "4, 2, left, default");
        
        showLbl = new JLabel("");
        showLbl.setIcon(IconsFactory.getChooseProtocolBtnIcon());
        add(showLbl, "6, 2, 3, 3, left, center");
        // CHECKSTYLE:LINES_1 AnonInnerLength
        showLbl.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mousePressed(final MouseEvent event) {
                setShowLabelIcon(IconsFactory.getChooseProtocolClickedBtnIcon());
            }
            
            @Override
            public void mouseClicked(final MouseEvent event) {
                ProtocolChooseManager.protocolChoosed(protocolNumber);
                setShowLabelIcon(IconsFactory.getChooseProtocolActiveBtnIcon());
            }
            
            @Override
            public void mouseEntered(final MouseEvent event) {
                setShowLabelIcon(IconsFactory.getChooseProtocolActiveBtnIcon());
            }
            
            @Override
            public void mouseExited(final MouseEvent event) {
                setShowLabelIcon(IconsFactory.getChooseProtocolBtnIcon());
            }
        });
        activeBar = new JProgressBar();
        activeBar.setValue(0);
        add(activeBar, "3, 4, 3, 1");
        nameLbl.setText(protocolName);
    }
    
    private void setShowLabelIcon(final Icon icon) {
        ProtocolPanel.this.setVisible(false);
        showLbl.setIcon(icon);
        ProtocolPanel.this.setVisible(true);
    }
    
    /**
     * Sets progress bar 0 or 100 value.
     * @param active protocol choose status
     */
    private void setActive(final boolean active) {
        this.activeBar.setValue(active ? 100 : 0);
    }
    
    @Override
    public void protocolChoosed(final int protocolNumber) {
        if (this.protocolNumber == protocolNumber) {
            setActive(true);
        } else {
            setActive(false);
        }
    }
}
