package view.components.stats;

import view.components.standard.DLabel;

import javax.swing.*;
import java.awt.*;

/**
 * A panel for displaying user statistics, including points, games played, and correct guesses.
 */
public class StatsPanel extends JPanel {

    private final DLabel statsLabel;

    /**
     * Constructs a StatsPanel with an initial layout and default statistics.
     */
    public StatsPanel() {
        // Initialize the stats label
        statsLabel = new DLabel("<html>Points: -<br>Games Played: -<br>Correct Guesses: -</html>");
        statsLabel.setFontSize(48);
        statsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Layout configuration
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50)); // Adjust padding to control overall spacing

        // Add the stats label to the top (NORTH) of the panel
        add(statsLabel, BorderLayout.NORTH);
    }

    /**
     * Updates the statistics displayed on the panel.
     *
     * @param points         the total points to display
     * @param gamesPlayed    the total games played to display
     * @param correctGuesses the total correct guesses to display
     */
    public void updateStats(int points, int gamesPlayed, int correctGuesses) {
        statsLabel.setText(String.format(
                "<html>Points: %d<br>Games Played: %d<br>Correct Guesses: %d</html>",
                points, gamesPlayed, correctGuesses
        ));
    }
}
