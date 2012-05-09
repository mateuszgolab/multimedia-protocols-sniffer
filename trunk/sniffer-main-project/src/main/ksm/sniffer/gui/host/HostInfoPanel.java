package ksm.sniffer.gui.host;

// CHECKSTYLE:OFF ClassDataAbstractionCoupling
// CHECKSTYLE:OFF MagicNumber

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import ksm.sniffer.core.net.HostInformationImpl;
import ksm.sniffer.core.net.IPAddressImpl;
import ksm.sniffer.core.net.NetworkInterfaceName;
import ksm.sniffer.gui.composite.IPInterfaceNameComboContainer;
import ksm.sniffer.gui.composite.IPInterfaceNameFiled;
import ksm.sniffer.gui.composite.IPInterfaceNameTextFieldsContainter;
import ksm.sniffer.gui.composite.PortFormattedField;
import ksm.sniffer.gui.composite.TransparentBackgroundPanel;
import ksm.sniffer.gui.resources.IconsFactory;
import ksm.sniffer.gui.resources.Messages;
import ksm.sniffer.module.api.net.HostIndex;
import ksm.sniffer.module.api.net.HostInformation;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * Panel contains one host informations GUI.
 */
@SuppressWarnings("serial")
public class HostInfoPanel extends TransparentBackgroundPanel {
    
    private static final String FONT_NAME = "Calibri";
    private IPInterfaceNameFiled ipField;
    private final HostIndex hostIndex;
    private PortFormattedField portField;
    
    
    /**
     * Constructs panel with given host index.
     * @param hostIndex host index
     */
    public HostInfoPanel(final HostIndex hostIndex) {
        super(IconsFactory.getHostInfoBackground());
        initialize(hostIndex);
        this.hostIndex = hostIndex;
    }
    
    /**
     * Initialize GUI.
     * @param hostIndex host index
     */
    private void initialize(final HostIndex hostIndex) {
        setLayout(new FormLayout(new ColumnSpec[] {FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("left:max(93dlu;default)"),
                FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("left:23dlu"), FormFactory.RELATED_GAP_COLSPEC},
                new RowSpec[] {FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow")}));
        
        final JLabel hostLbl = new JLabel(Messages.getString("HostInfoPanel.hostLbl.text")); //$NON-NLS-1$
        hostLbl.setForeground(Color.WHITE);
        hostLbl.setFont(new Font(FONT_NAME, Font.BOLD, 13));
        add(hostLbl, "2, 2, right, default");
        
        final JLabel lblNewLabel = new JLabel(hostIndex.toInteger().toString());
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font(FONT_NAME, Font.BOLD, 13));
        add(lblNewLabel, "4, 2, left, default");
        
        
        ipField = (hostIndex == HostIndex.FIRST) ? new IPInterfaceNameComboContainer()
                : new IPInterfaceNameTextFieldsContainter();
        
        final JLabel lblInterface = new JLabel(Messages.getString("HostInfoPanel.lblInterface.text")); //$NON-NLS-1$
        lblInterface.setFont(new Font(FONT_NAME, Font.BOLD, 13));
        lblInterface.setForeground(Color.WHITE);
        add(lblInterface, "2, 4, right, default");
        add(ipField.getInterfaceNameComponent(), "4, 4, 3, 1, fill, default");
        final JLabel labelIpAddres = new JLabel(Messages.getString("HostInfoPanel.labelIpAddres.text")); //$NON-NLS-1$
        labelIpAddres.setFont(new Font(FONT_NAME, Font.BOLD, 13));
        labelIpAddres.setForeground(Color.WHITE);
        add(labelIpAddres, "2, 6, right, default");
        add(ipField.getIPComponent(), "4, 6, 3, 1, fill, default");
        
        
        final JLabel labelInfo = new JLabel(Messages.getString("HostInfoPanel.lblNewLabel_2.text")); //$NON-NLS-1$
        labelInfo.setForeground(Color.WHITE);
        labelInfo.setFont(new Font(FONT_NAME, Font.BOLD, 13));
        add(labelInfo, "2, 8, right, default");
        
        portField = new PortFormattedField();
        portField.setText("");
        add(portField, "4, 8, fill, default");
        
        final JLabel iconLabel = new JLabel(IconsFactory.getHostInfoComputer()); //$NON-NLS-1$
        add(iconLabel, "6, 8, 1, 3, fill, center");
    }
    
    /**
     * Gets host information.
     * @return hostInformation
     */
    public HostInformation getHostInformation() {
        final Integer port = portField.getFormattedValue();
        final String ipAddr = ipField.getIPAddres();
        final NetworkInterfaceName interfaceName = ipField.getInterfaceName();
        return port != null && ipAddr != null && interfaceName != null ? new HostInformationImpl(new IPAddressImpl("/"
                + ipAddr, port), interfaceName.toString(), hostIndex) : null; // NOPMD: assigning an object to null
    }
    
    /**
     * Clear popups.
     */
    public void clear() {
        if (ipField != null) {
            ipField.clear();
        }
        if (portField != null) {
            portField.clear();
        }
    }
}
