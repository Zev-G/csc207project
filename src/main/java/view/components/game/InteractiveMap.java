package view.components.game;

import game.DistanceCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class InteractiveMap extends JPanel {

    private ImageIcon map;

    private double[] target;

    private boolean displayTarget = false;

    private boolean isSelected = false;

    private double[] chosenCoord = new double[2];

    private double[] mapLocation;

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
                        double size = Math.max(getHeight(), getWidth());
                        double yRatio = e.getY() / size;
                        double xRatio = e.getX() / size;
                        chosenCoord[0] = (mapLocation[1] - mapLocation[0]) * yRatio + mapLocation[0];
                        chosenCoord[1] = (mapLocation[3] - mapLocation[2]) * xRatio + mapLocation[2];
                        paintComponent(getGraphics());
                        System.out.println(chosenCoord[0] + " " + chosenCoord[1]);
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
            double xRatio = (chosenCoord[1] - mapLocation[2]) / (mapLocation[3] - mapLocation[2]);
            double yRatio = 1 + (chosenCoord[0] - mapLocation[1]) / (mapLocation[1] - mapLocation[0]);
            double size = Math.max(getHeight(), getWidth());
            g.fillOval((int) (size * xRatio - 5), (int) (size * yRatio - 5), 10, 10);
        }
        if (displayTarget) {
            g.setColor(new Color(255, 0, 0));
            double xRatio = (target[1] - mapLocation[2]) / (mapLocation[3] - mapLocation[2]);
            double yRatio = 1 + (target[0] - mapLocation[1]) / (mapLocation[1] - mapLocation[0]);
            double size = Math.max(getHeight(), getWidth());
            g.fillOval((int) (size * xRatio - 5), (int) (size * yRatio - 5), 10, 10);

        }
    }

    public double[] getChosenCoord() {
        return chosenCoord;
    }

    public void setTarget(double[] t) {
        target = t;
    }

    public void setDisplayTarget(boolean displayTarget) {
        this.displayTarget = displayTarget;
        paintComponent(getGraphics());
    }

    public double getDistance() {
        return DistanceCalculator.calculate(target, chosenCoord);
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
