package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ArrowButton extends JButton {

    public ArrowButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Define arrow shape
        int[] xPoints = {getWidth(), 0, getWidth()};
        int[] yPoints = {0, getHeight() / 2, getHeight()};

        // Fill arrow shape
        g2d.setColor(getBackground());
        g2d.fillPolygon(xPoints, yPoints, 3);

        // Draw text
        g2d.setColor(getForeground());
        g2d.setFont(getFont());
        FontMetrics fm = g2d.getFontMetrics();
        int textX = (getWidth() - fm.stringWidth(getText())) / 2;
        int textY = (getHeight() + fm.getAscent()) / 2 - 2;
        g2d.drawString(getText(), textX, textY);

        g2d.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No border needed
    }
}
