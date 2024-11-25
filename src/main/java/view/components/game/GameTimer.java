package view.components.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.TimerTask;

public class GameTimer implements ActionListener {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private JLabel timerLabel;

    private int totalTime;
    private int timeRemaining;

    private Timer timer;

    public GameTimer(int totalTime) {
        this.timerLabel = new JLabel(String.valueOf(totalTime));
        this.totalTime = totalTime;
        this.timeRemaining = totalTime;
        timer = new Timer(1000, this);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void resetTimer() {
        timeRemaining = totalTime;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timeRemaining--;
        timerLabel.setText(String.valueOf(timeRemaining));
        if (timeRemaining <= 0) {
            support.firePropertyChange("timeout", null, 0);
            stop();
        }
    }

    public JLabel getTimerLabel() {
        return timerLabel;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

}
