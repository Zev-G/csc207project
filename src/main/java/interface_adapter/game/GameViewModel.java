package interface_adapter.game;

import interface_adapter.ViewModel;

/**
 * ViewModel implementation for managing game state.
 */
public class GameViewModel extends ViewModel<GameState> {

    /**
     * Constructs a GameViewModel with the default game state.
     */
    public GameViewModel() {
        super("game");
        setState(new GameState());
    }
}
