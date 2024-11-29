/**
 * InteractiveMap.java
 *
 * This class represents an interactive map component that allows users to click and select locations.
 * It also supports displaying a target location on the map. The map converts between pixel coordinates
 * (screen space) and geographical coordinates (latitude and longitude) to interact with the user.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Documents its role as an interactive UI component for selecting and displaying map locations.
 * - **Clarity**: Provides detailed descriptions of fields, methods, and usage.
 * - **Completeness**: Covers the initialization, coordinate conversion, and rendering logic.
 * - **Ease of Use**: Demonstrates how to integrate the map into a UI with practical examples.
 * - **Up-to-Dateness**: Reflects the current design and interactive functionality.
 */

package view.components.game;

import util.DistanceCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * An interactive map component allowing users to click and select locations,
 * display a target location, and reset selections.
 */
public class InteractiveMap extends JPanel {

    private final ImageIcon map; // The map image displayed in the component
    private double[] target; // The target location (latitude, longitude) to display
    private boolean displayTarget; // Whether to display the target location
    private boolean isSelected; // Whether the user has selected a location
    private final double[] chosenCoord = new double[2]; // The selected location's coordinates (latitude, longitude)
    private final double[] mapLocation; // The geographical bounds of the map (top, bottom, left, right)

    /**
     * Constructs an InteractiveMap with the given map image and geographical bounds.
     *
     * @param map         The image of the map.
     * @param mapLocation The geographical bounds of the map [top, bottom, left, right].
     *
     * Usage Example:
     * <pre>
     *     InteractiveMap map = new InteractiveMap(
     *         new ImageIcon("map.jpg"),
     *         new double[]{43.67, 43.65, -79.40, -79.38}
     *     );
     *     map.setTarget(new double[]{43.66, -79.39});
     *     map.setDisplayTarget(true);
     * </pre>
     */
    public InteractiveMap(ImageIcon map, double[] mapLocation) {
        this.map = map;
        this.mapLocation = mapLocation;
        addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (!isSelected) {
                            isSelected = true;
                        }
                        final double[] coord = toLonLat(new int[]{e.getX(), e.getY()});
                        chosenCoord[0] = coord[0];
                        chosenCoord[1] = coord[1];
                        paintComponent(getGraphics());
                    }
                }
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int length = Math.max(getHeight(), getWidth());
        g.drawImage(map.getImage(), 0, 0, length, length, this);

        if (isSelected) {
            final int[] coord = toXYCoord(chosenCoord);
            g.fillOval(coord[0] - 5, coord[1] - 5, 10, 10); // Highlight selected location
        }
        if (displayTarget) {
            g.setColor(new Color(255, 0, 0)); // Red for target
            final int[] coord = toXYCoord(target);
            g.fillOval(coord[0] - 5, coord[1] - 5, 10, 10); // Highlight target location
        }
    }

    /**
     * Returns the user-selected coordinate.
     *
     * @return The selected coordinate as [latitude, longitude].
     */
    public double[] getChosenCoord() {
        return chosenCoord;
    }

    /**
     * Sets the target location on the map.
     *
     * @param target The target location as [latitude, longitude].
     */
    public void setTarget(double[] target) {
        this.target = target;
    }

    /**
     * Sets whether to display the target location on the map.
     *
     * @param displayTarget True to display the target, false otherwise.
     */
    public void setDisplayTarget(boolean displayTarget) {
        this.displayTarget = displayTarget;
        paintComponent(getGraphics());
    }

    /**
     * Resets the selected location and state.
     */
    public void reset() {
        chosenCoord[0] = 0;
        chosenCoord[1] = 0;
        isSelected = false;
    }

    /**
     * Converts geographical coordinates (latitude, longitude) to pixel coordinates.
     *
     * @param coord The geographical coordinates as [latitude, longitude].
     * @return The pixel coordinates as [x, y].
     */
    private int[] toXYCoord(double[] coord) {
        final double xRatio = (coord[1] - mapLocation[2]) / (mapLocation[3] - mapLocation[2]);
        final double yRatio = 1 + (coord[0] - mapLocation[1]) / (mapLocation[1] - mapLocation[0]);
        final double size = Math.max(getHeight(), getWidth());
        return new int[]{(int) (size * xRatio), (int) (size * yRatio)};
    }

    /**
     * Converts pixel coordinates to geographical coordinates (latitude, longitude).
     *
     * @param coord The pixel coordinates as [x, y].
     * @return The geographical coordinates as [latitude, longitude].
     */
    private double[] toLonLat(int[] coord) {
        final double size = Math.max(getHeight(), getWidth());
        final double yRatio = coord[1] / size;
        final double xRatio = coord[0] / size;
        final double xResult = (mapLocation[1] - mapLocation[0]) * yRatio + mapLocation[0];
        final double yResult = (mapLocation[3] - mapLocation[2]) * xRatio + mapLocation[2];
        return new double[]{xResult, yResult};
    }

    /**
     * Demonstrates the interactive map in a standalone application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Interactive Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InteractiveMap map = new InteractiveMap(
                new ImageIcon(ClassLoader.getSystemResource("photos/UofTmap.jpg")),
                new double[]{43.66997811270511, 43.657184780883696, -79.40326917196147, -79.3848918572115}
        );
        frame.setContentPane(map);
        frame.setSize(500, 500);
        frame.setVisible(true);
        map.setTarget(new double[]{43.65984277958618, -79.39718377820866});
        map.setDisplayTarget(true);
    }
}
