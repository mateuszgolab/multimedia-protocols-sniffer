package ksm.sniffer.gui.protocol;

// CHECKSTYLE:OFF MagicNumber

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ksm.sniffer.gui.resources.Messages;

/**
 * Panels that wraps protocols.
 */
@SuppressWarnings("serial")
public class MultipleProtocolsPanel extends JPanel {
    
    private static final int PANELS_GAP = 5;
    private int protocolsCount;
    
    /**
     * Constructs multiple protocols panel.
     */
    public MultipleProtocolsPanel() {
        initialize();
    }
    
    private void initialize() {
        setBorder(new TitledBorder(null, Messages.getString("MultipleProtocolsPanel.border.text"),
                TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
        setOpaque(false);
        setMaximumSize(new Dimension(250, 250));
        setPreferredSize(new Dimension(250, 250));
        setSize(new Dimension(250, 1200));
        setBounds(new Rectangle(0, 0, 100, 50));
        final FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, PANELS_GAP, PANELS_GAP);
        flowLayout.setAlignOnBaseline(true);
        setLayout(flowLayout);
    }
    
    /**
     * Adds new panel with protocol to this container.
     * @param protocolName protocol name
     * @param icon protocol's icon
     * @return protocol number
     */
    public Integer addProtocol(final String protocolName, final ImageIcon icon) {
        final ProtocolPanel protocol = new ProtocolPanel(protocolName, icon, ++protocolsCount);
        ProtocolChooseManager.addListener(protocol);
        final Dimension size = new Dimension(250, (int) (protocolsCount * protocol.getPreferredSize().getHeight()
                + protocolsCount * PANELS_GAP + 50));
        setMaximumSize(size);
        setPreferredSize(size);
        this.add(protocol);
        return protocolsCount;
    }
}
