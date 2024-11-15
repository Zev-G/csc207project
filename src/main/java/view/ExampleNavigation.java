package view;

import view.components.DFrame;
import view.pages.MainPanel;

import javax.swing.*;

public class ExampleNavigation {

    public static void main(String[] args) {
        final DFrame frame = new DFrame();
        frame.add(new MainPanel());
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
    }

}
