package ksm.sniffer.gui.host;

// CHECKSTYLE:OFF MagicNumber

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import ksm.sniffer.gui.composite.TransparentBackgroundPanel;
import ksm.sniffer.gui.resources.IconsFactory;
import ksm.sniffer.gui.resources.Messages;
import ksm.sniffer.module.api.net.HostCount;
import ksm.sniffer.module.api.net.Protocol;

/**
 * Panel that manages hosts.
 */
@SuppressWarnings("serial")
public class HostManagePanel extends TransparentBackgroundPanel {
    
    private static final String FONT_NAME = "Calibri";
    private JLabel startLabel;
    private JLabel error;
    
    /**
     * Constructs panel with given values.
     * @param protocol UDP or TCP
     * @param hostCount host count
     */
    public HostManagePanel(final Protocol protocol, final HostCount hostCount) {
        super(IconsFactory.getHostManageBackground());
        initialize(protocol.name(), hostCount.toInteger().toString());
    }
    
    /**
     * Initialize GUI.
     * @param protocol protocol name
     * @param hostCount host count
     */
    private void initialize(final String protocol, final String hostCount) {
        final JLabel lblNewLabel = new JLabel(Messages.getString("HostManagePanel.lblNewLabel.text")); //$NON-NLS-1$
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font(FONT_NAME, Font.BOLD, 13));
        
        final JLabel protocolTypeLbl = new JLabel(protocol);
        protocolTypeLbl.setForeground(Color.WHITE);
        protocolTypeLbl.setFont(new Font(FONT_NAME, Font.BOLD, 13));
        
        final JLabel labelOne = new JLabel(Messages.getString("HostManagePanel.lblNewLabel_2.text")); //$NON-NLS-1$
        labelOne.setForeground(Color.WHITE);
        labelOne.setFont(new Font(FONT_NAME, Font.BOLD, 13));
        
        final JLabel hostCountLbl = new JLabel(hostCount);
        hostCountLbl.setForeground(Color.WHITE);
        hostCountLbl.setFont(new Font(FONT_NAME, Font.BOLD, 13));
        
        startLabel = new JLabel("");
        startLabel.setIcon(IconsFactory.getStartPluginIcon());
        // CHECKSTYLE:OFF AnonInnerLength
        // Why empty lines counts?
        startLabel.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mousePressed(final MouseEvent event) {
                setLabelStartIcon(IconsFactory.getStartPluginClickedIcon());
            }
            
            @Override
            public void mouseClicked(final MouseEvent event) {
                setLabelStartIcon(IconsFactory.getStartPluginActiveIcon());
                StartManager.start();
            }
            
            @Override
            public void mouseEntered(final MouseEvent event) {
                setLabelStartIcon(IconsFactory.getStartPluginActiveIcon());
            }
            
            @Override
            public void mouseExited(final MouseEvent event) {
                setLabelStartIcon(IconsFactory.getStartPluginIcon());
            }
        });
        
        error = new JLabel(); //$NON-NLS-1$
        error.setFont(new Font(FONT_NAME, Font.PLAIN, 11));
        error.setForeground(Color.RED);
        final GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addComponent(startLabel)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.LEADING)
                                        .addGroup(
                                                groupLayout
                                                        .createSequentialGroup()
                                                        .addComponent(lblNewLabel)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(protocolTypeLbl)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(labelOne)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(hostCountLbl, GroupLayout.PREFERRED_SIZE, 16,
                                                                GroupLayout.PREFERRED_SIZE))
                                        .addComponent(error, GroupLayout.PREFERRED_SIZE, 224,
                                                GroupLayout.PREFERRED_SIZE)).addContainerGap(124, Short.MAX_VALUE)));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.LEADING)
                                        .addComponent(startLabel)
                                        .addGroup(
                                                groupLayout
                                                        .createSequentialGroup()
                                                        .addContainerGap()
                                                        .addGroup(
                                                                groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                        .addComponent(lblNewLabel)
                                                                        .addComponent(protocolTypeLbl)
                                                                        .addComponent(labelOne)
                                                                        .addComponent(hostCountLbl))
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(error))).addContainerGap(126, Short.MAX_VALUE)));
        setLayout(groupLayout);
    }
    
    /**
     * Gets error text.
     * @return error text
     */
    public String getErrorText() {
        return error.getText();
    }
    
    /**
     * Sets error text.
     * @param text error
     */
    public void setErrorText(final String text) {
        this.setVisible(false);
        error.setText(text);
        this.setVisible(true);
    }
    
    private void setLabelStartIcon(final Icon icon) {
        HostManagePanel.this.setVisible(false);
        startLabel.setIcon(icon);
        HostManagePanel.this.setVisible(true);
    }
}
