package use_case.game;

/**
 * A game output boundary.
 */
public interface GameOutputBoundary {

    /**
     * Handle the guess.
     * @param gameOutputData the game output data
     */
    void handleGuess(GameOutputData gameOutputData);

    /**
     * To init a game.
     * @param gameOutputData the game output data
     */
    void init(GameOutputData gameOutputData);

    /**
     * To end the current game.
     * @param gameOutputData the game output data
     */
    void endGame(GameOutputData gameOutputData);
}
