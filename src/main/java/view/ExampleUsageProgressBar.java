package view;

import view.components.game.SegmentedProgressBar;

import javax.swing.*;

public class ExampleUsageProgressBar {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Segmented Progress Bar Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int totalRounds = 10;
        SegmentedProgressBar progressBar = new SegmentedProgressBar(totalRounds);
        frame.add(progressBar);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Timer(1000, e -> {
            boolean won = Math.random() > 0.5;
            progressBar.updateRound(won);

            if (progressBar.getCurrentRound() >= totalRounds) {
                ((Timer) e.getSource()).stop();
            }
        }).start();
    }
}
