package view.components.standard;

import javax.swing.*;
import java.awt.*;

/**
 * A standard JFrame for the game GUI with a default configuration.
 */
public class DFrame extends JFrame {

    /**
     * Constructs a DFrame with default settings.
     * The frame uses a GridLayout and sets the title to "Game GUI".
     */
    public DFrame() {
        setTitle("Game GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());
    }
}
