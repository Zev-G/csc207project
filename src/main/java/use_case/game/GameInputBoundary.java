package use_case.game;

/**
 * Game output boundary.
 */
public interface GameInputBoundary {

    /**
     * Handle a user guess.
     *
     * @param input game input data
     */
    void handleGuess(GameInputData input);

    /**
     * To initiate a new game.
     */
    void init();
}
