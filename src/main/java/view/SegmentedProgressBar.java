package view;

import javax.swing.*;
import java.awt.*;

public class SegmentedProgressBar extends JPanel {
    private int totalRounds;
    int currentRound = 0;

    private static final Color PASTEL_GREEN = new Color(152, 251, 152);
    private static final Color PASTEL_RED = new Color(255, 182, 193);
    private static final Color DARKER_GRAY = new Color(169, 169, 169);

    public SegmentedProgressBar(int totalRounds) {
        this.totalRounds = totalRounds;
        setLayout(new GridLayout(1, totalRounds, 5, 0));
        setPreferredSize(new Dimension(0, 40));
        for (int i = 0; i < totalRounds; i++) {
            add(new SegmentPanel(Color.LIGHT_GRAY));
        }
        highlightNextSegment();
    }

    public boolean updateRound(boolean win) {
        if (currentRound < totalRounds) {
            Color fillColor = win ? PASTEL_GREEN : PASTEL_RED;
            SegmentPanel currentSegment = (SegmentPanel) getComponent(currentRound);
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
            SegmentPanel nextSegment = (SegmentPanel) getComponent(currentRound);
            nextSegment.setColor(DARKER_GRAY);
        }
    }

    private static class SegmentPanel extends JPanel {
        public SegmentPanel(Color color) {
            setBackground(color);
        }

        public void setColor(Color color) {
            setBackground(color);
        }
    }
}
