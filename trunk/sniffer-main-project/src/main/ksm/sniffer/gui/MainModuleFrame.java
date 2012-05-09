package ksm.sniffer.gui;

// CHECKSTYLE:OFF MagicNumber

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ksm.sniffer.gui.resources.IconsFactory;
import ksm.sniffer.gui.resources.Messages;

/**
 * Main frame class.
 */
@SuppressWarnings("serial")
public class MainModuleFrame extends JFrame {
    
    /**
     * Constructs frame.
     */
    public MainModuleFrame() {
        super();
        initializeLocationAndSieze();
        initializeMenuBar();
    }
    
    private void initializeLocationAndSieze() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int screenHeight = screenSize.height;
        final int screenWidth = screenSize.width;
        setLocation(screenWidth / 4, screenHeight / 4);
        setTitle(Messages.getString("MainModuleFrame.title"));
        setIconImage(IconsFactory.getMainFrameIcon().getImage());
    }
    
    private void initializeMenuBar() {
        final JMenuBar menuBar = new JMenuBar();
        final JMenu menuFile = new JMenu(Messages.getString("file"));
        final JMenuItem endItem = new JMenuItem(Messages.getString("file.close"));
        endItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent event) {
                MainModuleFrame.this.dispose();
            }
        });
        menuFile.add(endItem);
        menuBar.add(menuFile);
        this.setJMenuBar(menuBar);
    }
    
    @Override
    public void setContentPane(final Container contentPane) {
        super.setContentPane(contentPane);
        pack();
        setSize(getWidth(), 500);
    }
}
