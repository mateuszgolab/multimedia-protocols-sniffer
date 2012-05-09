package ksm.sniffer.gui.composite;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;

import ksm.sniffer.core.ModuleStarter;
import ksm.sniffer.core.net.NetworkInterfaceName;
import ksm.sniffer.gui.resources.Messages;

/**
 * Class represents component with interface and ip combo fields.
 */

public class IPInterfaceNameComboContainer implements IPInterfaceNameFiled {
    
    private JComboBox interfaceNameCombo;
    private JComboBox interfaceIPCombo;
    private Map<NetworkInterfaceName, List<String>> interfaceIPsMap = new HashMap<NetworkInterfaceName, List<String>>();
    private ToolTipPopup popupInterfaceName;
    private ToolTipPopup popupInterfaceIP;
    private JButton refreshButton;
    
    /**
     * Constructs panel.
     */
    public IPInterfaceNameComboContainer() {
        initialize();
        refresh();
    }
    
    private void initialize() {
        final ActionListener actionListener = new IPPanelActionListener();
        refreshButton = new JButton(Messages.getString("HostInfoPanel.btnNewButton.text_1"));
        refreshButton.addActionListener(actionListener);
        interfaceIPCombo = new JComboBox();
        interfaceIPCombo.addActionListener(actionListener);
        interfaceNameCombo = new JComboBox();
        interfaceNameCombo.addActionListener(actionListener);
        popupInterfaceIP = new ToolTipPopup(interfaceIPCombo, Messages.getString("error.ip"));
        popupInterfaceName = new ToolTipPopup(interfaceNameCombo, Messages.getString("error.interface"));
    }
    
    private void setComboItems(final JComboBox combo, final Collection<String> items) {
        combo.removeAllItems();
        for (String item : items) {
            combo.addItem(item);
        }
        combo.updateUI();
    }
    
    private void setComboNetworkInterfaces(final JComboBox combo, final List<NetworkInterfaceName> items) {
        combo.removeAllItems();
        Collections.sort(items, new Comparator<NetworkInterfaceName>() {
            
            @Override
            public int compare(final NetworkInterfaceName object1, final NetworkInterfaceName object2) {
                return object1.toString().compareTo(object2.toString());
            }
            
        });
        for (NetworkInterfaceName item : items) {
            combo.addItem(item);
        }
        combo.updateUI();
    }
    
    private void refresh() {
        interfaceIPsMap = ModuleStarter.getNetworkInterfaces();
        setComboNetworkInterfaces(interfaceNameCombo, new ArrayList<NetworkInterfaceName>(interfaceIPsMap.keySet()));
        interfaceNameCombo.setSelectedIndex(-1);
    }
    
    @Override
    public NetworkInterfaceName getInterfaceName() {
        final NetworkInterfaceName selectedItem = (NetworkInterfaceName) interfaceNameCombo.getSelectedItem();
        if (selectedItem == null || selectedItem.toString().length() == 0) {
            popupInterfaceName.showPopup();
            return null;
        }
        return selectedItem;
    }
    
    @Override
    public String getIPAddres() {
        final String selectedItem = (String) interfaceIPCombo.getSelectedItem();
        if (selectedItem == null || selectedItem.length() == 0) {
            popupInterfaceIP.showPopup();
            return null;
        }
        return selectedItem;
    }
    
    /**
     * Clears popups.
     */
    public void clear() {
        popupInterfaceIP.hidePopup();
        popupInterfaceName.hidePopup();
    }
    
    @Override
    public JComboBox getIPComponent() {
        return interfaceIPCombo;
    }
    
    @Override
    public JComboBox getInterfaceNameComponent() {
        return interfaceNameCombo;
    }
    
    /**
     * Gets refresh button.
     * @return interfaceNameCombo
     */
    public JButton getRefreshButton() {
        return refreshButton;
    }
    
    private class IPPanelActionListener implements ActionListener {
        
        @Override
        public void actionPerformed(final ActionEvent event) {
            final Object source = event.getSource();
            if (source.equals(interfaceIPCombo)) {
                popupInterfaceIP.hidePopup();
            } else if (source.equals(interfaceNameCombo)) {
                if (interfaceNameCombo.getSelectedIndex() == -1) {
                    interfaceIPCombo.setEnabled(false);
                    interfaceIPCombo.setSelectedIndex(-1);
                } else {
                    popupInterfaceIP.hidePopup();
                    popupInterfaceName.hidePopup();
                    setComboItems(interfaceIPCombo, interfaceIPsMap.get(interfaceNameCombo.getSelectedItem()));
                    interfaceIPCombo.setEnabled(true);
                }
            } else if (source.equals(refreshButton)) {
                refresh();
            }
            
        }
        
    }
    
    
}
