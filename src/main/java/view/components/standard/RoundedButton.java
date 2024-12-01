package view.components.standard;

import javax.swing.*;
import java.awt.*;

/**
 * A custom button with rounded corners and configurable styling.
 */
public class RoundedButton extends JButton {

    /**
     * Constructs a RoundedButton with the specified text.
     *
     * @param text the text displayed on the button
     */
    public RoundedButton(String text) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setFont(new Font("Arial", Font.BOLD, 18));
        setBackground(Color.LIGHT_GRAY);
        setForeground(Color.BLACK);
    }

    /**
     * Paints the button with rounded corners and a background color.
     *
     * @param g the graphics context
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(g);
        g2.dispose();
    }

    /**
     * Paints the border of the button with rounded corners.
     *
     * @param g the graphics context
     */
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.GRAY);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
        g2.dispose();
    }
}
