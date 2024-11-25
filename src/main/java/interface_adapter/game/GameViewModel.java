package interface_adapter.game;

import interface_adapter.ViewModel;

public class GameViewModel extends ViewModel<GameState> {

    public GameViewModel() {
        super("game");
        setState(new GameState());
    }
}
