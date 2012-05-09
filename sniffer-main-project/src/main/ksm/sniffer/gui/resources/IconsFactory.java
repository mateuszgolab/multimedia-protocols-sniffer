package ksm.sniffer.gui.resources;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Class used to get icons and images from resources.
 */
public final class IconsFactory {
    
    private IconsFactory() {
    }
    
    /**
     * Gets choose protocol Button icon.
     * @return button icon
     */
    public static Icon getChooseProtocolClickedBtnIcon() {
        return new ImageIcon(IconsFactory.class.getResource("nextClicked.png"));
    }
    
    
    /**
     * Gets choose protocol Button icon.
     * @return button icon
     */
    public static Icon getChooseProtocolBtnIcon() {
        return new ImageIcon(IconsFactory.class.getResource("next.png"));
    }
    
    /**
     * Gets choose protocol active Button icon.
     * @return button icon
     */
    public static Icon getChooseProtocolActiveBtnIcon() {
        return new ImageIcon(IconsFactory.class.getResource("nextActive.png"));
    }
    
    /**
     * Gets protocol icon.
     * @return icon
     */
    public static Icon getDefaultIcon() {
        return new ImageIcon(IconsFactory.class.getResource("chart.png"));
    }
    
    /**
     * Gets host manage background.
     * @return background
     */
    public static Image getHostManageBackground() {
        return new ImageIcon(IconsFactory.class.getResource("hostManageBackground.png")).getImage();
    }
    
    /**
     * Gets host info background.
     * @return background
     */
    public static Image getHostInfoBackground() {
        return new ImageIcon(IconsFactory.class.getResource("hostInfoBackground.png")).getImage();
    }
    
    /**
     * Gets protocol background.
     * @return background
     */
    public static Image getProtocolBackground() {
        return new ImageIcon(IconsFactory.class.getResource("protocolBackground.png")).getImage();
    }
    
    /**
     * Gets host info computer icon.
     * @return icon
     */
    public static Icon getHostInfoComputer() {
        return new ImageIcon(IconsFactory.class.getResource("computer_info.png"));
    }
    
    /**
     * Gets start button icon.
     * @return button icon
     */
    public static Icon getStartPluginIcon() {
        return new ImageIcon(IconsFactory.class.getResource("play.png"));
    }
    
    /**
     * Gets start active button icon.
     * @return button icon
     */
    public static Icon getStartPluginActiveIcon() {
        return new ImageIcon(IconsFactory.class.getResource("playActive.png"));
    }
    
    /**
     * Gets start clicked button icon.
     * @return button icon
     */
    public static Icon getStartPluginClickedIcon() {
        return new ImageIcon(IconsFactory.class.getResource("playClicked.png"));
    }
    
    /**
     * Gets main frame icon.
     * @return button icon
     */
    public static ImageIcon getMainFrameIcon() {
        return new ImageIcon(IconsFactory.class.getResource("filter.png"));
    }
}
