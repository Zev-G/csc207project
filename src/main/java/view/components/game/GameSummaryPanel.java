package view.components.game;

import view.components.standard.DLabel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel for displaying the game summary, including points scored and guess results.
 */
public class GameSummaryPanel extends JPanel {

    private final DLabel pointsLabel;
    private final JPanel guessesPanel;
    private final JLabel discoverText;

    /**
     * Constructs a GameSummaryPanel with initial UI components.
     */
    public GameSummaryPanel() {
        // Initialize "Discover UofT" text
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
        add(discoverText, BorderLayout.NORTH); // "Discover UofT" at the top
        add(pointsLabel, BorderLayout.CENTER); // Points in the middle
        add(guessesPanel, BorderLayout.SOUTH); // Guesses at the bottom
    }

    /**
     * Updates the stats displayed on the panel.
     *
     * @param points   the total points to display
     * @param guessBar the list of Boolean values representing correct (true) or incorrect (false) guesses
     */
    public void updateSummary(int points, List<Boolean> guessBar) {
        // Update points label
        pointsLabel.setText(String.format("<html><b>Points:</b> %d</html>", points)); // Bold "Points"

        // Update guesses panel
        guessesPanel.removeAll(); // Clear previous guesses
        for (Boolean guess : guessBar) {
            JLabel guessLabel = new JLabel(guess ? "\u2713" : "\u2717"); // Checkmark or cross
            guessLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 48)); // Larger font size for ticks/crosses
            guessLabel.setForeground(guess ? Color.GREEN : Color.RED); // Green for correct, red for incorrect
            guessesPanel.add(guessLabel);
        }

        guessesPanel.revalidate();
        guessesPanel.repaint();
    }
}
