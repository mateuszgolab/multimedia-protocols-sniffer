package ksm.sniffer.gui;

// CHECKSTYLE:OFF MagicNumber

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import ksm.sniffer.core.ModuleStarter;
import ksm.sniffer.gui.composite.GradientPanel;
import ksm.sniffer.gui.host.MultipleHostsPanel;
import ksm.sniffer.gui.host.StartListener;
import ksm.sniffer.gui.host.StartManager;
import ksm.sniffer.gui.protocol.MultipleProtocolsPanel;
import ksm.sniffer.gui.protocol.ProtocolChooseListener;
import ksm.sniffer.gui.protocol.ProtocolChooseManager;
import ksm.sniffer.gui.resources.Messages;
import ksm.sniffer.module.api.Module;
import ksm.sniffer.module.api.net.HostInformation;
import ksm.sniffer.module.loader.LoadException;
import ksm.sniffer.module.loader.PluginLoader;

import org.apache.log4j.Logger;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Main panel that wraps other panels.
 */
@SuppressWarnings("serial")
public class MainPanel extends GradientPanel implements ProtocolChooseListener, StartListener {
    
    private static final Logger LOG = Logger.getLogger(MainPanel.class);
    private final Map<Integer, Module> idModuleMap = new HashMap<Integer, Module>();
    private int loaded = -1;
    private MultipleHostsPanel multipleHostsPanel;
    private MultipleProtocolsPanel multipleProtocolsPanel;
    
    /**
     * Constructs main panel.
     */
    public MainPanel() {
        initialize();
        loadProtocols();
    }
    
    /**
     * Initialize GUI.
     */
    private void initialize() {
        setBackground(new Color(150, 207, 246));
        setForeground(new Color(30, 65, 104));
        final FormLayout layout = new FormLayout("5px,pref,10px,pref", "pref:grow");
        final CellConstraints cellConstraints = new CellConstraints();
        setLayout(layout);
        StartManager.addListener(this);
        multipleProtocolsPanel = new MultipleProtocolsPanel();
        
        multipleHostsPanel = new MultipleHostsPanel();
        add(multipleProtocolsPanel, cellConstraints.xy(2, 1, CellConstraints.RIGHT, CellConstraints.FILL));
        add(multipleHostsPanel, cellConstraints.xy(4, 1, CellConstraints.LEFT, CellConstraints.FILL));
        ProtocolChooseManager.addListener(this);
    }
    
    /**
     * Starts loading protocols.
     */
    private void loadProtocols() {
        new PluginLoaderWorker().execute();
    }
    
    @Override
    public void protocolChoosed(final int protocolNumber) {
        if (loaded != protocolNumber) {
            multipleHostsPanel.setHostInfo(idModuleMap.get(protocolNumber).getConnectionInformation());
            loaded = protocolNumber;
        }
    }
    
    @Override
    public void start() {
        final List<HostInformation> hInfos = multipleHostsPanel.getHostsInformation();
        if (hInfos != null) {
            try {
                ModuleStarter.startModule(hInfos, idModuleMap.get(loaded));
            } catch (Exception e) {
                multipleHostsPanel.setErrorMessage(Messages.getString("error.start.module"));
                JOptionPane.showMessageDialog(this, e.getMessage(), Messages.getString("erro.start.module"),
                        JOptionPane.ERROR_MESSAGE);
                LOG.error("Can't start module", e);
            }
        }
    }
    
    private class PluginLoaderWorker extends SwingWorker<Collection<Module>, Void> {
        
        @Override
        protected Collection<Module> doInBackground() throws LoadException {
            return PluginLoader.getInstance().getModuleContainer().getAllModules();
        }
        
        @Override
        protected void done() {
            try {
                multipleProtocolsPanel.setVisible(false);
                for (Module module : get()) {
                    final Integer moduleID = multipleProtocolsPanel.addProtocol(module.getName(),
                            module.getIconURL() != null ? new ImageIcon(module.getIconURL()) : null);
                    idModuleMap.put(moduleID, module);
                }
                multipleProtocolsPanel.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainPanel.this, Messages.getString("module.loading.error"));
                LOG.error("Modules loading error", e);
            }
        }
    }
}
