package interface_adapter.mgame;

import interface_adapter.ViewModel;

/**
 * ViewModel for the multiplayer game end state.
 * This class extends the generic ViewModel class to manage the state
 * specific to the end of a multiplayer game.
 */
public class MGameEndViewModel extends ViewModel<GameEndState> {

    /**
     * Constructs a new MGameEndViewModel with the default identifier.
     * The identifier is used to distinguish this ViewModel in the application.
     */
    public MGameEndViewModel() {
        super("multiplayerEnd");
    }
}
