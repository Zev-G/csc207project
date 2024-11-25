package use_case.mgame;

import use_case.game.GameOutputBoundary;

/**
 * The Multiplayer Game output boundary.
 */
public interface MGameOutputBoundary extends GameOutputBoundary {

    /**
     * Wait for opponent response.
     */
    void waitForResponse();

    /**
     * To show an error page.
     */
    void PrepareError();

}
