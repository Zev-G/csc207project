package view.utils;

import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * ImageScaler is a utility class for scaling images to a specified width and height.
 */
public class ImageScaler {

    /**
     * Scales the given ImageIcon to the specified width and height.
     *
     * @param imageIcon The ImageIcon to be scaled.
     * @param width The desired width of the scaled image.
     * @param height The desired height of the scaled image.
     * @return A new ImageIcon containing the scaled image.
     */
    public static ImageIcon getScaledImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}
