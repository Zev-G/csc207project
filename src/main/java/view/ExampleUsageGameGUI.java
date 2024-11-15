package view;

import view.pages.GamePage;
import view.pages.PageFrame;

import javax.swing.*;

public class ExampleUsageGameGUI {
    public static void main(String[] args) {
        // Initialize and start the game GUI
        final PageFrame frame = new PageFrame();
        frame.navigate(new GamePage(frame));
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
    }
}
