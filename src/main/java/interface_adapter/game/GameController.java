package interface_adapter.game;

import use_case.game.GameInputBoundary;
import use_case.game.GameInputData;

/**
 * Adapter class to translate between the GameInputBoundary and the GameInputData.
 */
public class GameController {

    private final GameInputBoundary gameInteractor;

    /**
     * Constructs a new GameController with the specified game interactor.
     *
     * @param gameInteractor The GameInputBoundary object to handle game logic.
     */
    public GameController(GameInputBoundary gameInteractor) {
        this.gameInteractor = gameInteractor;
    }

    /**
     * Handles a user's guess in the game.
     *
     * @param photoID The ID of the photo being guessed.
     * @param target  The target color values.
     * @param chosen  The color values chosen by the user.
     */
    public void handleGuess(int photoID, double[] target, double[] chosen) {
        gameInteractor.handleGuess(new GameInputData(photoID, target, chosen));
    }

    /**
     * Initializes the game.
     */
    public void init(){
        this.gameInteractor.init();
    }

    /**
     * Handles a timeout event in the game.
     *
     * @param photoID The ID of the photo for which the timeout occurred.
     * @param target  The target color values.
     * @param chosen  The color values chosen by the user at the time of timeout.
     */
    public void timeout(int photoID, double[] target, double[] chosen) {
        gameInteractor.timeout(new GameInputData(photoID, target, chosen));
    }
}
