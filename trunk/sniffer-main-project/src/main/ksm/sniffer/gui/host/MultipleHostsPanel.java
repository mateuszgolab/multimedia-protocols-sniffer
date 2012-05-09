package ksm.sniffer.gui.host;

// CHECKSTYLE:OFF MagicNumber

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ksm.sniffer.gui.resources.Messages;
import ksm.sniffer.module.api.net.ConnectionInformation;
import ksm.sniffer.module.api.net.HostIndex;
import ksm.sniffer.module.api.net.HostInformation;

/**
 * Panel that wraps hostInformation panels and manages them.
 */
@SuppressWarnings("serial")
public class MultipleHostsPanel extends JPanel {
    
    private static final int PANELS_GAP = 5;
    private HostManagePanel manageHost;
    
    /**
     * Constructs panel.
     */
    public MultipleHostsPanel() {
        initialize();
    }
    
    private void initialize() {
        setBorder(new TitledBorder(null, Messages.getString("MultipleHostsPanel.border.text"), TitledBorder.LEADING,
                TitledBorder.TOP, null, Color.WHITE));
        setOpaque(false);
        setMaximumSize(new Dimension(350, 250));
        setPreferredSize(new Dimension(350, 250));
        setSize(new Dimension(350, 1200));
        setBounds(new Rectangle(0, 0, 100, 50));
        final FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, PANELS_GAP, PANELS_GAP);
        flowLayout.setAlignOnBaseline(true);
        setLayout(flowLayout);
    }
    
    /**
     * gets host informations from HostInfoPanles.
     * @return hosts infos
     */
    public List<HostInformation> getHostsInformation() {
        final List<HostInformation> list = new ArrayList<HostInformation>();
        for (Component c : this.getComponents()) {
            if (c instanceof HostInfoPanel) {
                list.add(((HostInfoPanel) c).getHostInformation());
            }
        }
        boolean error = false;
        for (HostInformation h : list) {
            if (h == null) {
                error = true;
                break;
            }
        }
        manageHost.setErrorText(error ? Messages.getString("Host.error") : "");
        return error ? null : list; // NOPMD: assigning an object to null
    }
    
    /**
     * Adds info.getHostCount() HostInfosPanles and HostManagePanel.
     * @param info connection information
     */
    public void setHostInfo(final ConnectionInformation info) {
        setVisible(false);
        for (Component c : this.getComponents()) {
            if (c instanceof HostInfoPanel) {
                ((HostInfoPanel) c).clear();
            }
        }
        this.removeAll();
        manageHost = new HostManagePanel(info.getProtocol(), info.getHostCount());
        this.add(manageHost);
        
        int prefHeight = -1;
        for (int i = 0; i < info.getHostCount().toInt(); i++) {
            final HostInfoPanel panel = new HostInfoPanel(HostIndex.values()[i]);
            if (prefHeight < 0) {
                prefHeight = (int) panel.getPreferredSize().getHeight();
            }
            this.add(panel);
        }
        // Set preferred size to show scroll when its needed
        final Dimension size = new Dimension(350, (int) (info.getHostCount().toInt() * prefHeight
                + info.getHostCount().toInt() * PANELS_GAP + 50 + manageHost.getHeight()));
        this.setPreferredSize(size);
        setVisible(true);
    }
    
    /**
     * Sets error message.
     * @param error error text
     */
    public void setErrorMessage(final String error) {
        manageHost.setErrorText(error);
    }
}
