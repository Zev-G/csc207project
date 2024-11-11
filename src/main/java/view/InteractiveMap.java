package view;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class InteractiveMap extends JPanel {

    private ImageIcon map;

    private double[] chosenCoord = new double[2];

    private double [] mapLocation;

    private int [] mouseLocation = new int[2];

    public InteractiveMap(ImageIcon map, double[] mapLocation) {
        this.map = map;
        this.mapLocation = mapLocation;
        addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        double yRatio = (double) e.getY() /getHeight();
                        double xRatio = (double) e.getX() /getWidth();
                        mouseLocation[0] = e.getX();
                        mouseLocation[1] = e.getY();
                        chosenCoord[0] = (mapLocation[1]-mapLocation[0])*xRatio+mapLocation[0];
                        chosenCoord[1] = (mapLocation[3]-mapLocation[2])*yRatio+mapLocation[2];
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
        g.fillOval(mouseLocation[0]-5,mouseLocation[1]-5,10,10);
    }

    public double[] getChosenCoord() {
        return chosenCoord;
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Interactive Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InteractiveMap map = new InteractiveMap(new ImageIcon(ClassLoader.getSystemResource("photos/UofTmap.jpg")), new double[]{0,1,0,1});
        frame.setContentPane(map);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
