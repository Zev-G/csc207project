package view.components.game;

import javax.swing.*;
import java.awt.*;

/**
 * Displays and manages the player's points in the game.
 */
public class PointsDisplay {

    private final JLabel pointsLabel;
    private int points;

    /**
     * Constructs a PointsDisplay with an initial points value.
     *
     * @param initialPoints the starting points
     */
    public PointsDisplay(int initialPoints) {
        this.points = initialPoints;
        this.pointsLabel = new JLabel(" | " + String.format("%04d", points) + " Points");
        this.pointsLabel.setFont(new Font("Arial", Font.BOLD, 18));
    }

    /**
     * Returns the JLabel displaying the points.
     *
     * @return the points label
     */
    public JLabel getPointsLabel() {
        return pointsLabel;
    }

    /**
     * Increments the points by a specified value and updates the display.
     *
     * @param increment the amount to add to the current points
     */
    public void incrementPoints(int increment) {
        points += increment;
        pointsLabel.setText(" | " + String.format("%04d", points) + " Points");
    }

    /**
     * Sets the points to a specific value and updates the display.
     *
     * @param points the new points value
     */
    public void setPoints(int points) {
        this.points = points;
        pointsLabel.setText(" | " + String.format("%04d", points) + " Points");
    }
}
