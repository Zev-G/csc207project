package view.components.stats;

import view.components.standard.DLabel;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {

    private final DLabel statsLabel;

    public StatsPanel() {
        // Initialize the label
        statsLabel = new DLabel("<html>Points: -<br>Games Played: -<br>Correct Guesses: -</html>");
        statsLabel.setFontSize(48);
        statsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Layout configuration
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50)); // Adjust padding to control overall spacing

        // Add the single label to the top (NORTH) of the panel
        add(statsLabel, BorderLayout.NORTH);
    }

    /**
     * Updates the stats displayed on the label.
     *
     * @param points        The points to display.
     * @param gamesPlayed   The games played to display.
     * @param correctGuesses The correct guesses to display.
     */
    public void updateStats(int points, int gamesPlayed, int correctGuesses) {
        statsLabel.setText(String.format(
                "<html>Points: %d<br>Games Played: %d<br>Correct Guesses: %d</html>",
                points, gamesPlayed, correctGuesses
        ));
    }
}
