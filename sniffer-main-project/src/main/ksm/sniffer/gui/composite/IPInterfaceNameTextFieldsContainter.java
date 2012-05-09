package ksm.sniffer.gui.composite;

import java.awt.Component;

import javax.swing.JTextField;

import ksm.sniffer.core.net.NetworkInterfaceName;
import ksm.sniffer.gui.resources.Messages;

/**
 * Class that contains ip and port field.
 */
public class IPInterfaceNameTextFieldsContainter implements IPInterfaceNameFiled {
    private final IPFormattedField ipField;
    private final JTextField interfaceNameField;
    
    /**
     * Constructs object.
     */
    public IPInterfaceNameTextFieldsContainter() {
        ipField = new IPFormattedField();
        interfaceNameField = new PortFormattedField();
    }
    
    @Override
    public String getIPAddres() {
        return ipField.getFormattedValue();
    }
    
    @Override
    public NetworkInterfaceName getInterfaceName() {
        return new NetworkInterfaceName(interfaceNameField.getText().trim().length() > 0 ? interfaceNameField.getText()
                : Messages.getString("unknown_interface"));
    }
    
    @Override
    public Component getInterfaceNameComponent() {
        return interfaceNameField;
    }
    
    @Override
    public Component getIPComponent() {
        return ipField;
    }
    
    @Override
    public void clear() {
        ipField.clear();
        
    }
    
}
