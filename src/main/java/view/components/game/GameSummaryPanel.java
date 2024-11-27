package view.components.stats;

import view.components.standard.DLabel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameSummaryPanel extends JPanel {

    private final DLabel pointsLabel;
    private final JPanel guessesPanel;

    public GameSummaryPanel() {
        // Initialize points label
        pointsLabel = new DLabel("<html>Points: -</html>");
        pointsLabel.setFontSize(48);
        pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialize guesses panel
        guessesPanel = new JPanel();
        guessesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Spacing between ticks/crosses

        // Layout configuration
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50)); // Adjust padding to control overall spacing

        // Add components
        add(pointsLabel, BorderLayout.NORTH);
        add(guessesPanel, BorderLayout.CENTER);
    }

    /**
     * Updates the stats displayed on the panel.
     *
     * @param points    The points to display.
     * @param guessBar  The list of booleans representing correct (true) or incorrect (false) guesses.
     */
    public void updateSummary(int points, List<Boolean> guessBar) {
        // Update points label
        pointsLabel.setText(String.format("<html>Points: %d</html>", points));

        // Update guesses panel
        guessesPanel.removeAll(); // Clear previous guesses
        for (Boolean guess : guessBar) {
            JLabel guessLabel = new JLabel(guess ? "Correct" : "Incorrect");
            guessLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Large font for ticks and crosses
            guessLabel.setForeground(guess ? Color.GREEN : Color.RED); // Green for ticks, red for crosses
            guessesPanel.add(guessLabel);
        }
        guessesPanel.revalidate();
        guessesPanel.repaint();
    }
}
