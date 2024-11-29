package view.components.game;

import view.ViewConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A segmented progress bar for tracking progress across multiple rounds.
 * Each segment represents a round and is color-coded based on win/loss status.
 */
public class SegmentedProgressBar extends JPanel {

    private static final Color DARKER_GRAY = new Color(169, 169, 169);
    private static final Color PASTEL_GREEN = new Color(152, 251, 152);
    private static final Color PASTEL_RED = new Color(255, 182, 193);

    private final int totalRounds;
    private int currentRound;

    /**
     * Constructs a SegmentedProgressBar with the specified number of rounds.
     *
     * @param totalRounds the total number of rounds to display in the progress bar
     */
    public SegmentedProgressBar(int totalRounds) {
        this.totalRounds = totalRounds;
        setLayout(new GridLayout(1, totalRounds, ViewConstants.GAP_S, 0));
        setPreferredSize(new Dimension(0, ViewConstants.HEIGHT_SS));
        for (int i = 0; i < totalRounds; i++) {
            add(new SegmentPanel(Color.LIGHT_GRAY));
        }
        highlightNextSegment();
    }

    /**
     * Updates the progress bar for the current round based on win/loss status.
     *
     * @param win {@code true} if the round was won, {@code false} otherwise
     * @return {@code true} if the progress bar has reached the last round, {@code false} otherwise
     */
    public boolean updateRound(boolean win) {
        if (currentRound < totalRounds) {
            final Color fillColor = win ? PASTEL_GREEN : PASTEL_RED;
            final SegmentPanel currentSegment = (SegmentPanel) getComponent(currentRound);
            currentSegment.setColor(fillColor);
            currentRound++;
            repaint();
            if (currentRound < totalRounds) {
                highlightNextSegment();
            }
        }
        return currentRound == totalRounds;
    }

    /**
     * Highlights the next segment in the progress bar to indicate the current round.
     */
    private void highlightNextSegment() {
        if (currentRound < totalRounds) {
            final SegmentPanel nextSegment = (SegmentPanel) getComponent(currentRound);
            nextSegment.setColor(DARKER_GRAY);
        }
    }

    /**
     * Returns the current round index (0-based).
     *
     * @return the current round
     */
    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * Returns a list of booleans representing the win/loss status of all segments.
     *
     * @return a list where {@code true} represents a win (green segment) and {@code false} otherwise
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
     * Returns the count of segments that represent wins.
     *
     * @return the number of segments with a win (green color)
     */
    public int getCountTrueSegments() {
        List<Boolean> statuses = getAllSegmentStatus();
        int trueCount = 0;
        for (Boolean status : statuses) {
            if (status) {
                trueCount++;
            }
        }
        return trueCount;
    }

    /**
     * Resets the progress bar to its initial state.
     */
    public void reset() {
        currentRound = 0;
        for (int i = 0; i < totalRounds; i++) {
            SegmentPanel segment = (SegmentPanel) getComponent(i);
            segment.setColor(Color.LIGHT_GRAY);
        }
        highlightNextSegment();
        repaint();
    }

    /**
     * A panel representing an individual segment in the progress bar.
     */
    private static class SegmentPanel extends JPanel {

        /**
         * Constructs a SegmentPanel with the specified initial color.
         *
         * @param color the initial color of the segment
         */
        protected SegmentPanel(Color color) {
            setBackground(color);
        }

        /**
         * Sets the color of the segment.
         *
         * @param color the new color of the segment
         */
        protected void setColor(Color color) {
            setBackground(color);
        }
    }
}
