package ksm.sniffer.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import ksm.sniffer.gui.resources.Messages;
import ksm.sniffer.logger.LoggerConfigurator;

import org.apache.log4j.Logger;

/**
 * Class with main method that start application.
 */
public final class MainModuleGui {
    
    static {
        LoggerConfigurator.configure();
    }
    
    private static final String REQUIRED_JAVA_VERSION = "1.6.0_10";
    private static final Logger LOG = Logger.getLogger(MainModuleGui.class);
    private JFrame frame;
    
    /**
     * Create the application.
     */
    private MainModuleGui() {
        initialize();
    }
    
    /**
     * Start the application.
     * @param args application arguments.
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                try {
                    final MainModuleGui window = new MainModuleGui();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    LOG.error("Problem with starting JFrame", e);
                }
            }
        });
    }
    
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        final String javaVersion = System.getProperty("java.version");
        LOG.info("Java version: " + javaVersion);
        if (REQUIRED_JAVA_VERSION.compareTo(javaVersion) > 0) {
            JOptionPane.showMessageDialog(
                    null,
                    Messages.getString("java.version.installed") + javaVersion + "\n"
                            + Messages.getString("java.version.required") + REQUIRED_JAVA_VERSION + " "
                            + Messages.getString("java.version.unstable"));
        }
        
        try {
            UIManager.put("nimbusBlueGrey", Color.DARK_GRAY);
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            LOG.error("Can't load nimbus look and feel", e);
        }
        frame = new MainModuleFrame();
        final JScrollPane scroll = new JScrollPane(new MainPanel());
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        frame.setContentPane(scroll);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
