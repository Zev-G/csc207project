/**
 * SegmentedProgressBar.java
 *
 * This class represents a progress bar with multiple segments, visually indicating progress through rounds.
 * Each segment can represent a win (green), a loss (red), or a pending round (gray).
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Documents its role as a segmented progress bar with dynamic visual updates.
 * - **Clarity**: Provides clear descriptions of its fields, methods, and functionality.
 * - **Completeness**: Covers initialization, updates, and reset functionality.
 * - **Ease of Use**: Demonstrates how to integrate the component into a larger game UI.
 * - **Up-to-Dateness**: Reflects the current implementation details and segment interaction logic.
 */

package view.components.game;

import view.ViewConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A segmented progress bar for tracking progress across multiple rounds.
 * Each segment visually represents the outcome of a round (win/loss) or a pending state.
 */
public class SegmentedProgressBar extends JPanel {

    private static final Color DARKER_GRAY = new Color(169, 169, 169); // Highlight color for next round
    private static final Color PASTEL_GREEN = new Color(152, 251, 152); // Win color
    private static final Color PASTEL_RED = new Color(255, 182, 193); // Loss color

    private int totalRounds; // Total number of segments
    private int currentRound; // Current progress index

    /**
     * Constructs a SegmentedProgressBar with the specified number of segments.
     *
     * @param totalRounds The total number of segments (rounds) in the progress bar.
     *
     * Usage Example:
     * <pre>
     *     SegmentedProgressBar progressBar = new SegmentedProgressBar(5);
     *     progressBar.updateRound(true); // Updates first segment as a win
     * </pre>
     */
    public SegmentedProgressBar(int totalRounds) {
        this.totalRounds = totalRounds;
        setLayout(new GridLayout(1, totalRounds, ViewConstants.GAP_S, 0)); // Horizontal layout for segments
        setPreferredSize(new Dimension(0, ViewConstants.HEIGHT_SS)); // Set height for visual consistency

        // Initialize segments with a default gray color
        for (int i = 0; i < totalRounds; i++) {
            add(new SegmentPanel(Color.LIGHT_GRAY));
        }
        highlightNextSegment(); // Highlight the first segment
    }

    /**
     * Updates the current round's segment based on the outcome (win/loss).
     *
     * - Marks the segment green for a win.
     * - Marks the segment red for a loss.
     * - Highlights the next segment, if applicable.
     *
     * @param win True for a win, false for a loss.
     * @return True if this was the last round, false otherwise.
     */
    public boolean updateRound(boolean win) {
        if (currentRound < totalRounds) {
            final Color fillColor = win ? PASTEL_GREEN : PASTEL_RED;
            final SegmentPanel currentSegment = (SegmentPanel) getComponent(currentRound);
            currentSegment.setColor(fillColor); // Update current segment color
            currentRound++;
            repaint(); // Refresh the UI

            if (currentRound < totalRounds) {
                highlightNextSegment(); // Highlight the next segment
            }
        }
        return currentRound == totalRounds; // Return whether this was the last round
    }

    /**
     * Highlights the next segment in the progress bar.
     */
    private void highlightNextSegment() {
        if (currentRound < totalRounds) {
            final SegmentPanel nextSegment = (SegmentPanel) getComponent(currentRound);
            nextSegment.setColor(DARKER_GRAY); // Highlight next segment
        }
    }

    /**
     * Returns the current round index.
     *
     * @return The current round (0-indexed).
     */
    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * Returns the win/loss status of all segments.
     *
     * @return A list of booleans where true represents a win (green segment), false otherwise.
     */
    public List<Boolean> getAllSegmentStatus() {
        List<Boolean> statuses = new ArrayList<>();
        for (int i = 0; i < totalRounds; i++) {
            SegmentPanel segment = (SegmentPanel) getComponent(i);
            statuses.add(segment.getBackground().equals(PASTEL_GREEN));
        }
        return statuses;
    }

    /**
     * Counts the number of segments marked as wins.
     *
     * @return The count of green segments (wins).
     */
    public int getCountTrueSegments() {
        List<Boolean> statuses = getAllSegmentStatus(); // Retrieve statuses
        int trueCount = 0;

        // Count the number of true (win) statuses
        for (Boolean status : statuses) {
            if (status) {
                trueCount++;
            }
        }

        return trueCount; // Return the count of wins
    }

    /**
     * Resets the progress bar to its initial state.
     * All segments are set to the default gray color, and the first segment is highlighted.
     */
    public void reset() {
        currentRound = 0; // Reset the current round index
        for (int i = 0; i < totalRounds; i++) {
            SegmentPanel segment = (SegmentPanel) getComponent(i);
            segment.setColor(Color.LIGHT_GRAY); // Reset segment color
        }
        highlightNextSegment(); // Highlight the first segment
        repaint(); // Refresh the UI
    }

    /**
     * Inner class representing an individual segment of the progress bar.
     * Each segment is a small panel with a customizable background color.
     */
    private static class SegmentPanel extends JPanel {

        /**
         * Constructs a SegmentPanel with the specified initial color.
         *
         * @param color The initial background color of the segment.
         */
        protected SegmentPanel(Color color) {
            setBackground(color);
        }

        /**
         * Updates the color of the segment.
         *
         * @param color The new background color.
         */
        protected void setColor(Color color) {
            setBackground(color);
        }
    }
}
