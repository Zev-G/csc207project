package view.components.game;

import view.ViewConstants;

import javax.swing.*;
import java.awt.*;

/**
 * A progress bar with several segments.
 */
public class SegmentedProgressBar extends JPanel {
    private static final Color DARKER_GRAY = new Color(169, 169, 169);
    private static final Color PASTEL_GREEN = new Color(152, 251, 152);
    private static final Color PASTEL_RED = new Color(255, 182, 193);
    private int totalRounds;
    private int currentRound;

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
     * Updates the current round to whether or not the round was won.
     * @param win whether the user won
     * @return if the currentRound is the last round
     */
    public boolean updateRound(boolean win) {
        if (currentRound < totalRounds) {
            final Color fillColor;
            if (win) {
                fillColor = PASTEL_GREEN;
            }
            else {
                fillColor = PASTEL_RED;
            }
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

    private void highlightNextSegment() {
        if (currentRound < totalRounds) {
            final SegmentPanel nextSegment = (SegmentPanel) getComponent(currentRound);
            nextSegment.setColor(DARKER_GRAY);
        }
    }

    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * Represents one segment.
     */
    private static class SegmentPanel extends JPanel {

        protected SegmentPanel(Color color) {
            setBackground(color);
        }

        protected void setColor(Color color) {
            setBackground(color);
        }
    }

}
