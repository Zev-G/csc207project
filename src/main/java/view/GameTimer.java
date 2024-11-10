package view;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private JLabel timerLabel;
    private int timeRemaining;
    private Timer timer;

    public GameTimer(int initialTime) {
        this.timeRemaining = initialTime;
        this.timerLabel = new JLabel(formatTime(timeRemaining));
        this.timerLabel.setFont(new Font("Arial", Font.BOLD, 18));
    }

    public JLabel getTimerLabel() {
        return timerLabel;
    }

    public void start(Runnable onTimeUp) {
        stop();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (timeRemaining > 0) {
                    timeRemaining--;
                    timerLabel.setText(formatTime(timeRemaining));
                } else {
                    timer.cancel();
                    onTimeUp.run();
                }
            }
        }, 0, 1000);
    }

    public void reset(int newTime) {
        stop();
        timeRemaining = newTime;
        timerLabel.setText(formatTime(timeRemaining));
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("%d:%02d", minutes, secs);
    }
}
