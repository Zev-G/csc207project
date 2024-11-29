/**
 * StatsPanel.java
 *
 * This class represents a UI component for displaying user statistics in a structured panel.
 * It is built using Swing and provides an easily customizable layout for presenting stats such as
 * points, games played, and correct guesses.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Documents its role as a UI component for displaying statistics.
 * - **Clarity**: Provides clear descriptions of its layout, fields, and methods.
 * - **Completeness**: Covers initialization, layout configuration, and update methods.
 * - **Ease of Use**: Simplifies integration into larger UI systems with straightforward methods.
 * - **Up-to-Dateness**: Reflects the current implementation and usage details.
 */

package view.components.stats;

import view.components.standard.DLabel;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for displaying user statistics.
 * Provides a visually appealing layout to present statistics like points, games played, and correct guesses.
 */
public class StatsPanel extends JPanel {

    private final DLabel statsLabel;

    /**
     * Constructs a StatsPanel with a pre-configured layout and label.
     *
     * Features:
     * - Displays statistics using a central label with large font size.
     * - Offers a clean and structured design with padding and alignment.
     */
    public StatsPanel() {
        // Initialize the label
        statsLabel = new DLabel("<html>Points: -<br>Games Played: -<br>Correct Guesses: -</html>");
        statsLabel.setFontSize(48); // Large font size for visibility
        statsLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text

        // Layout configuration
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50)); // Add padding around the panel

        // Add the single label to the top (NORTH) of the panel
        add(statsLabel, BorderLayout.NORTH);
    }

    /**
     * Updates the statistics displayed on the panel.
     *
     * @param points         The total points to display.
     * @param gamesPlayed    The total number of games played to display.
     * @param correctGuesses The total number of correct guesses to display.
     *
     * Usage Example:
     * <pre>
     *     StatsPanel statsPanel = new StatsPanel();
     *     statsPanel.updateStats(100, 10, 7);
     * </pre>
     */
    public void updateStats(int points, int gamesPlayed, int correctGuesses) {
        statsLabel.setText(String.format(
                "<html>Points: %d<br>Games Played: %d<br>Correct Guesses: %d</html>",
                points, gamesPlayed, correctGuesses
        ));
    }
}
