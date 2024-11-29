package view.components.game;

import util.DistanceCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Represents an interactive, clickable map for selecting coordinates.
 */
public class InteractiveMap extends JPanel {

    private final ImageIcon map;
    private final double[] mapLocation; // [top, bottom, left, right] latitude and longitude

    private double[] target;
    private boolean displayTarget;
    private boolean isSelected;
    private final double[] chosenCoord = new double[2];

    /**
     * Creates an interactive map.
     *
     * @param map         the map image to display
     * @param mapLocation the geographic boundaries of the map [top, bottom, left, right]
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
            g.fillOval(coord[0] - 5, coord[1] - 5, 10, 10); // Draw user-selected point
        }

        if (displayTarget) {
            g.setColor(new Color(255, 0, 0)); // Red color for target
            final int[] coord = toXYCoord(target);
            g.fillOval(coord[0] - 5, coord[1] - 5, 10, 10); // Draw target point
        }
    }

    /**
     * Returns the user-selected coordinates.
     *
     * @return the user-selected coordinates as [latitude, longitude]
     */
    public double[] getChosenCoord() {
        return chosenCoord;
    }

    /**
     * Sets the target location on the map.
     *
     * @param target the target coordinates [latitude, longitude]
     */
    public void setTarget(double[] target) {
        this.target = target;
    }

    /**
     * Controls whether the target location is displayed on the map.
     *
     * @param displayTarget {@code true} to display the target, {@code false} to hide it
     */
    public void setDisplayTarget(boolean displayTarget) {
        this.displayTarget = displayTarget;
        paintComponent(getGraphics());
    }

    /**
     * Resets the map, clearing any selected coordinates.
     */
    public void reset() {
        chosenCoord[0] = 0;
        chosenCoord[1] = 0;
        isSelected = false;
    }

    /**
     * Converts geographic coordinates (latitude, longitude) to map pixel coordinates.
     *
     * @param coord the geographic coordinates [latitude, longitude]
     * @return the corresponding pixel coordinates [x, y]
     */
    private int[] toXYCoord(double[] coord) {
        final double xRatio = (coord[1] - mapLocation[2]) / (mapLocation[3] - mapLocation[2]);
        final double yRatio = 1 + (coord[0] - mapLocation[1]) / (mapLocation[1] - mapLocation[0]);
        final double size = Math.max(getHeight(), getWidth());
        return new int[]{(int) (size * xRatio), (int) (size * yRatio)};
    }

    /**
     * Converts map pixel coordinates to geographic coordinates (latitude, longitude).
     *
     * @param coord the pixel coordinates [x, y]
     * @return the corresponding geographic coordinates [latitude, longitude]
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
     * Main method for testing the interactive map component.
     *
     * @param args command-line arguments
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
