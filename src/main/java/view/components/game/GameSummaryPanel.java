/**
 * GameSummaryPanel.java
 *
 * This class represents a UI component for displaying a summary of the user's game performance.
 * It includes the user's points, a visual representation of guesses (correct and incorrect),
 * and a motivational message encouraging further exploration.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Clearly defines its role as a game summary display panel.
 * - **Clarity**: Provides straightforward descriptions of layout and update functionality.
 * - **Completeness**: Covers initialization, layout configuration, and update methods.
 * - **Ease of Use**: Demonstrates how to integrate and update the panel in a larger UI.
 * - **Up-to-Dateness**: Reflects the current implementation details.
 */

package view.components.game;

import view.components.standard.DLabel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel for displaying a summary of the user's game performance.
 * Provides a clean and organized layout for showing points, guesses (ticks and crosses), and motivational text.
 */
public class GameSummaryPanel extends JPanel {

    private final DLabel pointsLabel;
    private final JPanel guessesPanel;
    private final JLabel discoverText;

    /**
     * Constructs a GameSummaryPanel with a pre-configured layout and components.
     *
     * Features:
     * - Displays motivational text at the top.
     * - Shows the user's total points in a bold and prominent style in the center.
     * - Visualizes guesses (correct and incorrect) using ticks and crosses at the bottom.
     */
    public GameSummaryPanel() {
        // Initialize Discover UofT text
        discoverText = new JLabel("Discover UofT to get more points!");
        discoverText.setFont(new Font("Arial", Font.PLAIN, 60)); // Font size 60, unbolded
        discoverText.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize points label
        pointsLabel = new DLabel("<html><b>Points:</b> -</html>");
        pointsLabel.setFontSize(70); // Font size 70, bold for "Points"
        pointsLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the points label

        // Initialize guesses panel
        guessesPanel = new JPanel();
        guessesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center the ticks/crosses

        // Layout configuration
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50)); // Adjust padding to control overall spacing

        // Add components
        add(discoverText, BorderLayout.NORTH); // Discover UofT at the top
        add(pointsLabel, BorderLayout.CENTER); // Points in the middle
        add(guessesPanel, BorderLayout.SOUTH); // Guesses at the bottom
    }

    /**
     * Updates the summary panel with the provided points and guesses.
     *
     * @param points    The total points to display.
     * @param guessBar  A list of booleans representing correct (true) or incorrect (false) guesses.
     *
     * Responsibilities:
     * - Updates the points label with the provided value.
     * - Refreshes the guesses panel to display ticks (\u2713) for correct guesses and crosses (\u2717) for incorrect guesses.
     *
     * Usage Example:
     * <pre>
     *     GameSummaryPanel summaryPanel = new GameSummaryPanel();
     *     summaryPanel.updateSummary(100, List.of(true, false, true));
     * </pre>
     */
    public void updateSummary(int points, List<Boolean> guessBar) {
        // Update points label
        pointsLabel.setText(String.format("<html><b>Points:</b> %d</html>", points)); // Bold "Points"

        // Update guesses panel
        guessesPanel.removeAll(); // Clear previous guesses
        for (Boolean guess : guessBar) {
            JLabel guessLabel = new JLabel(guess ? "\u2713" : "\u2717");
            guessLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 48)); // Larger font size for ticks/crosses
            guessLabel.setForeground(guess ? Color.GREEN : Color.RED); // Green for ticks, red for crosses
            guessesPanel.add(guessLabel);
        }

        guessesPanel.revalidate();
        guessesPanel.repaint();
    }
}
