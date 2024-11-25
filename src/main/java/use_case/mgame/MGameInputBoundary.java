package use_case.mgame;

import use_case.game.GameInputBoundary;

import java.net.Socket;

/**
 * Multiplayer Game input boundary.
 */
public interface MGameInputBoundary extends GameInputBoundary {

    /**
     * To start a multiplayer game.
     *
     * @param seed   the seed to generate images
     * @param socket the socket to communicate with the server
     */
    void startMGame(long seed, Socket socket);
}
