package interface_adapter.multiplayer;

import interface_adapter.ViewModel;

/**
 * The MultiplayerViewModel class extends the generic ViewModel class
 * and provides a specific implementation for the multiplayer context.
 * It initializes the state to "connect" and sets the view type as "multiplayer."
 */
public class MultiplayerViewModel extends ViewModel<String> {

    /**
     * Constructs a MultiplayerViewModel instance.
     * Sets the initial state to "connect" and defines the view type as "multiplayer."
     */
    public MultiplayerViewModel() {
        super("multiplayer"); // Specify the view type as "multiplayer"
        this.setState("connect"); // Initialize the state to "connect"
    }
}
