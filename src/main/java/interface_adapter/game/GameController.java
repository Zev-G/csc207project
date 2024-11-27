package interface_adapter.game;

import use_case.game.GameInputBoundary;
import use_case.game.GameInputData;

public class GameController {

    final GameInputBoundary gameInteractor;

    public GameController(GameInputBoundary gameInteractor) {
        this.gameInteractor = gameInteractor;
    }

    public void handleGuess(int photoID, double[] target, double[] chosen) {
        gameInteractor.handleGuess(new GameInputData(photoID, target, chosen));
    }

    public void init(){
        this.gameInteractor.init();
    }

    public void timeout(int photoID, double[] target, double[] chosen) {
        gameInteractor.timeout(new GameInputData(photoID, target, chosen));
    }
}
