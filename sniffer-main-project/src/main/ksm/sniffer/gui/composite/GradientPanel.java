package ksm.sniffer.gui.composite;

// CHECKSTYLE:OFF MagicNumber
/*
 * soapUI, copyright (C) 2004-2009 eviware.com
 * soapUI is free software; you can redistribute it and/or modify it under the
 * terms of version 2.1 of the GNU Lesser General Public License as published by
 * the Free Software Foundation.
 * soapUI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details at gnu.org.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Paint;

import javax.swing.JPanel;

/**
 * Panel with gradient from foreground to background.
 */
@SuppressWarnings("serial")
public class GradientPanel extends JPanel {
    
    /**
     * Gradient directions.
     */
    public enum GradientDirection {
        /**
         * Diagonal left gradient direction.
         */
        DIAGONAL_LEFT,
        /**
         * Diagonal right gradient direction.
         */
        DIAGONAL_RIGHT,
        /**
         * Horizontal gradient direction.
         */
        HORIZONTAL,
        /**
         * Vertical gradient direction.
         */
        VERTICAL
        
    }
    
    
    private boolean cyclic;
    private GradientDirection direction = GradientDirection.HORIZONTAL;
    private int maxLength;
    
    /**
     * Constructs panel with diagonal left gradient direction.
     */
    public GradientPanel() {
        this(GradientDirection.DIAGONAL_LEFT);
        
    }
    
    /**
     * Constructs panel with given gradient direction.
     * @param direction gradient direction
     */
    public GradientPanel(final GradientDirection direction) {
        super(new BorderLayout());
        setOpaque(false);
        this.direction = direction;
    }
    
    /**
     * Constructs panel with given layout manager.
     * @param layoutManager {@link LayoutManager}
     */
    public GradientPanel(final LayoutManager layoutManager) {
        super(layoutManager);
        setOpaque(false);
        this.direction = GradientDirection.HORIZONTAL;
    }
    
    
    @Override
    public void paintComponent(final Graphics graphics) {
        if (isOpaque()) {
            super.paintComponent(graphics);
            return;
        }
        
        final int width = getWidth();
        final int height = getHeight();
        
        // Create the gradient paint
        GradientPaint paint = null;
        
        final Color foreground = getForeground();
        final Color background = getBackground();
        
        switch (direction) {
            case HORIZONTAL:
                paint = new GradientPaint(0, height / 2, foreground, width, height / 2, background, cyclic);
                break;
            case VERTICAL:
                paint = new GradientPaint(width / 2, 0, foreground, width / 2, maxLength > 0 ? maxLength : height,
                        background, cyclic);
                break;
            case DIAGONAL_LEFT:
                paint = new GradientPaint(0, 0, foreground, width, height, background, cyclic);
                break;
            case DIAGONAL_RIGHT:
                paint = new GradientPaint(width, 0, foreground, 0, height, background, cyclic);
                break;
            default:// Horizontal
                paint = new GradientPaint(0, height / 2, foreground, width, height / 2, background, cyclic);
                break;
        }
        // we need to cast to Graphics2D for this operation
        final Graphics2D g2d = (Graphics2D) graphics;
        
        // save the old paint
        final Paint oldPaint = g2d.getPaint();
        
        // set the paint to use for this operation
        g2d.setPaint(paint);
        
        // fill the background using the paint
        g2d.fillRect(0, 0, width, height);
        
        // restore the original paint
        g2d.setPaint(oldPaint);
        
        super.paintComponent(graphics);
    }
}
