package view.components.game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A game timer that counts down from a specified total time, updates a JLabel,
 * and notifies listeners when the timer reaches zero.
 */
public class GameTimer implements ActionListener {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final JLabel timerLabel;

    private final int totalTime;
    private int timeRemaining;

    private final Timer timer;

    /**
     * Constructs a GameTimer with a specified total time.
     *
     * @param totalTime the total time (in seconds) for the timer
     */
    public GameTimer(int totalTime) {
        this.timerLabel = new JLabel(String.valueOf(totalTime));
        this.totalTime = totalTime;
        this.timeRemaining = totalTime;
        this.timer = new Timer(1000, this);
    }

    /**
     * Starts the timer countdown.
     */
    public void start() {
        timer.start();
    }

    /**
     * Stops the timer countdown.
     */
    public void stop() {
        timer.stop();
    }

    /**
     * Resets the timer to its initial total time.
     */
    public void resetTimer() {
        timeRemaining = totalTime;
        timerLabel.setText(String.valueOf(totalTime));
    }

    /**
     * Handles timer tick events, updating the timer label and notifying listeners
     * when the time reaches zero.
     *
     * @param e the action event triggered by the timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        timeRemaining--;
        timerLabel.setText(String.valueOf(timeRemaining));
        if (timeRemaining <= 0) {
            support.firePropertyChange("timeout", null, 0);
            stop();
        }
    }

    /**
     * Returns the JLabel displaying the timer value.
     *
     * @return the timer label
     */
    public JLabel getTimerLabel() {
        return timerLabel;
    }

    /**
     * Adds a listener to be notified of property changes, such as timeout events.
     *
     * @param listener the property change listener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }
}
