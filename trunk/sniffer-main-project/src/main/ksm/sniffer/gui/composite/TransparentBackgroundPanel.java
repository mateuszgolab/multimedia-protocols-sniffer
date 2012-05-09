package ksm.sniffer.gui.composite;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * Panel with transparent background.
 */
@SuppressWarnings("serial")
public class TransparentBackgroundPanel extends JPanel {
    
    private final Image img;
    
    /**
     * Constructs panel with image background.
     * @param img background image
     */
    public TransparentBackgroundPanel(final Image img) {
        this.img = img;
        final Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
    }
    
    @Override
    protected void paintComponent(final Graphics graphics) {
        graphics.drawImage(img, 0, 0, null);
        super.paintComponents(graphics);
    }
}
