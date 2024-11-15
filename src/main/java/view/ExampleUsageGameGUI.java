package view;

import view.components.standard.DFrame;
import view.pages.GamePanel;

import javax.swing.*;

public class ExampleUsageGameGUI {
    public static void main(String[] args) {
        // Initialize and start the game GUI
        final DFrame frame = new DFrame();
        frame.add(new GamePanel());
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
    }
}
