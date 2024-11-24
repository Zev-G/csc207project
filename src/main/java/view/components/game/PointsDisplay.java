package view.components.game;

import javax.swing.*;
import java.awt.*;

public class PointsDisplay {
    private JLabel pointsLabel;
    private int points;

    public PointsDisplay(int initialPoints) {
        this.points = initialPoints;
        this.pointsLabel = new JLabel(" | " + String.format("%04d", points) + " Points");
        this.pointsLabel.setFont(new Font("Arial", Font.BOLD, 18));
    }

    public JLabel getPointsLabel() {
        return pointsLabel;
    }

    public void incrementPoints(int increment) {
        points += increment;
        pointsLabel.setText(" | " + String.format("%04d", points) + " Points");
    }

    public void setPoints(int points) {
        this.points = points;
        pointsLabel.setText(" | " + String.format("%04d", points) + " Points");
    }
}
