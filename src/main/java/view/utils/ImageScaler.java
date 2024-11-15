package view.utils;

import javax.swing.ImageIcon;
import java.awt.Image;

public class ImageScaler {
    public static ImageIcon getScaledImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}
