package view.components.game;

import util.DistanceCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This represents an interactive map that is clickable.
 */
public class InteractiveMap extends JPanel {

    private final ImageIcon map;

    private double[] target;

    private boolean displayTarget;

    private boolean isSelected;

    private final double[] chosenCoord = new double[2];

    private final double[] mapLocation;

    /**
     * This creates a interactive map.
     * @param map image of the map.
     * @param mapLocation the top, down, left, right longitude and latitude.
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
            g.fillOval(coord[0] - 5, coord[1] - 5, 10, 10);
        }
        if (displayTarget) {
            g.setColor(new Color(255, 0, 0));
            final int[] coord = toXYCoord(target);
            g.fillOval(coord[0] - 5, coord[1] - 5, 10, 10);

        }
    }

    /**
     * Get user selected coordinate.
     * @return the user selected coordinate.
     */
    public double[] getChosenCoord() {
        return chosenCoord;
    }

    /**
     * Set the target location.
     * @param target the target location.
     */
    public void setTarget(double[] target) {
        this.target = target;
    }

    /**
     * Set if display the target.
     * @param displayTarget if display the target.
     */
    public void setDisplayTarget(boolean displayTarget) {
        this.displayTarget = displayTarget;
        paintComponent(getGraphics());
    }

    private int[] toXYCoord(double[] coord) {
        final double xRatio = (coord[1] - mapLocation[2]) / (mapLocation[3] - mapLocation[2]);
        final double yRatio = 1 + (coord[0] - mapLocation[1]) / (mapLocation[1] - mapLocation[0]);
        final double size = Math.max(getHeight(), getWidth());
        return new int[]{(int) (size * xRatio), (int) (size * yRatio)};
    }

    private double[] toLonLat(int[] coord) {
        final double size = Math.max(getHeight(), getWidth());
        final double yRatio = coord[1] / size;
        final double xRatio = coord[0] / size;
        final double xResult = (mapLocation[1] - mapLocation[0]) * yRatio + mapLocation[0];
        final double yResult = (mapLocation[3] - mapLocation[2]) * xRatio + mapLocation[2];
        return new double[]{xResult, yResult};
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Interactive Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InteractiveMap map = new InteractiveMap(new ImageIcon(ClassLoader.getSystemResource("photos/UofTmap.jpg")),
                new double[]{43.66997811270511, 43.657184780883696, -79.40326917196147, -79.3848918572115});
        frame.setContentPane(map);
        frame.setSize(500, 500);
        frame.setVisible(true);
        map.setTarget(new double[]{43.65984277958618, -79.39718377820866});
        map.setDisplayTarget(true);
    }
}
