package view.components.stats;

import view.components.standard.DLabel;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {

    private final DLabel pointsLabel;
    private final DLabel gamesPlayedLabel;
    private final DLabel correctGuessesLabel;

    public StatsPanel() {
        // Initialize labels
        pointsLabel = new DLabel("Points: -");
        gamesPlayedLabel = new DLabel("Games Played: -");
        correctGuessesLabel = new DLabel("Correct Guesses: -");

        // Configure labels
        pointsLabel.setFontSize(48);
        pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gamesPlayedLabel.setFontSize(48);
        gamesPlayedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        correctGuessesLabel.setFontSize(48);
        correctGuessesLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Layout configuration
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);

        // Add labels to the panel
        add(pointsLabel);
        add(gamesPlayedLabel);
        add(correctGuessesLabel);
    }

    /**
     * Updates the stats displayed on the panel.
     *
     * @param points        The points to display.
     * @param gamesPlayed   The games played to display.
     * @param correctGuesses The correct guesses to display.
     */
    public void updateStats(int points, int gamesPlayed, int correctGuesses) {
        pointsLabel.setText("Points: " + points);
        gamesPlayedLabel.setText("Games Played: " + gamesPlayed);
        correctGuessesLabel.setText("Correct Guesses: " + correctGuesses);
    }
}
