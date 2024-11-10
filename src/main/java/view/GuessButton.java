package view;

import javax.swing.*;
import java.awt.*;

public class GuessButton {
    private JButton guessButton;


    // THIS BUTTON IS MOSTLY JUST TEMPORARY AS I DON'T KNOW WHAT LOGIC WE WANT
    public GuessButton() {
        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 16));
        guessButton.setPreferredSize(new Dimension(100, 40));
    }

    public JButton getButton() {
        return guessButton;
    }

    public void addActionListener(java.awt.event.ActionListener listener) {
        guessButton.addActionListener(listener);
    }
}
