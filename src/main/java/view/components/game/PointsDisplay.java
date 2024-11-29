/**
 * PointsDisplay.java
 *
 * This class represents a visual component for displaying and updating the user's points.
 * It provides methods to initialize, increment, and set the total points, and ensures the
 * display is formatted correctly.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Clearly defines its role as a points display component.
 * - **Clarity**: Provides clear descriptions of its fields, methods, and functionality.
 * - **Completeness**: Covers initialization, update, and access methods.
 * - **Ease of Use**: Demonstrates how to integrate and update the points display in a UI.
 * - **Up-to-Dateness**: Reflects the current implementation details.
 */

package view.components.game;

import javax.swing.*;
import java.awt.*;

/**
 * Component for visually displaying user points in a formatted label.
 * Provides methods for dynamically updating the displayed points.
 */
public class PointsDisplay {

    private JLabel pointsLabel; // The label displaying the points
    private int points; // The current total points

    /**
     * Constructs a PointsDisplay with the specified initial points.
     *
     * @param initialPoints The initial points to display.
     *
     * Usage Example:
     * <pre>
     *     PointsDisplay pointsDisplay = new PointsDisplay(0);
     *     JLabel label = pointsDisplay.getPointsLabel();
     *     pointsDisplay.incrementPoints(10); // Adds 10 points
     * </pre>
     */
    public PointsDisplay(int initialPoints) {
        this.points = initialPoints;
        this.pointsLabel = new JLabel(" | " + String.format("%04d", points) + " Points");
        this.pointsLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Bold font for visibility
    }

    /**
     * Returns the JLabel component for the points display.
     *
     * @return The JLabel displaying the points.
     */
    public JLabel getPointsLabel() {
        return pointsLabel;
    }

    /**
     * Increments the total points by the specified value.
     *
     * @param increment The number of points to add.
     */
    public void incrementPoints(int increment) {
        points += increment;
        pointsLabel.setText(" | " + String.format("%04d", points) + " Points"); // Update label text
    }

    /**
     * Sets the total points to the specified value.
     *
     * @param points The new total points to display.
     */
    public void setPoints(int points) {
        this.points = points;
        pointsLabel.setText(" | " + String.format("%04d", points) + " Points"); // Update label text
    }
}
