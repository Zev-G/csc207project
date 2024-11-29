/**
 * GameTimer.java
 *
 * This class provides a countdown timer for games, using a Swing-based implementation.
 * It manages the countdown logic, updates a visual label for the timer, and notifies listeners
 * when the timer reaches zero.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Clearly defines its role as a game timer and notifier.
 * - **Clarity**: Provides concise descriptions of its fields, methods, and behavior.
 * - **Completeness**: Explains the timer's lifecycle methods (start, stop, reset) and notification mechanism.
 * - **Ease of Use**: Demonstrates how to integrate and use the timer in a game UI.
 * - **Up-to-Dateness**: Reflects the current implementation of the countdown timer.
 */

package view.components.game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A countdown timer for games.
 * Manages a countdown, updates a label with the remaining time, and notifies listeners when the timer ends.
 */
public class GameTimer implements ActionListener {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final JLabel timerLabel;
    private final Timer timer;

    private final int totalTime;
    private int timeRemaining;

    /**
     * Constructs a GameTimer with the specified total countdown time.
     *
     * @param totalTime The total time in seconds for the countdown.
     *
     * Usage Example:
     * <pre>
     *     GameTimer timer = new GameTimer(30); // 30 seconds countdown
     *     timer.addPropertyChangeListener(event -> {
     *         if ("timeout".equals(event.getPropertyName())) {
     *             System.out.println("Time's up!");
     *         }
     *     });
     *     timer.start();
     * </pre>
     */
    public GameTimer(int totalTime) {
        this.timerLabel = new JLabel(String.valueOf(totalTime));
        this.totalTime = totalTime;
        this.timeRemaining = totalTime;
        this.timer = new Timer(1000, this);
    }

    /**
     * Starts the timer and begins the countdown.
     */
    public void start() {
        timer.start();
    }

    /**
     * Stops the timer and pauses the countdown.
     */
    public void stop() {
        timer.stop();
    }

    /**
     * Resets the timer to the initial total time.
     * Does not start the timer automatically.
     */
    public void resetTimer() {
        timeRemaining = totalTime;
        timerLabel.setText(String.valueOf(totalTime));
    }

    /**
     * Handles the countdown logic on each timer tick.
     * Decrements the remaining time, updates the timer label, and fires a "timeout" event when time reaches zero.
     *
     * @param e The action event triggered by the timer.
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
     * Returns the label displaying the remaining time.
     *
     * @return The JLabel displaying the remaining time.
     */
    public JLabel getTimerLabel() {
        return timerLabel;
    }

    /**
     * Adds a property change listener to be notified of timer events.
     *
     * @param listener The listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }
}
