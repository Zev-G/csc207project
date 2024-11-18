package use_case.multiplayer;

import java.net.Socket;

/**
 * This represents an output data.
 */
public class MultiplayerOutputData {

    private final long seed;

    private final Socket socket;

    /**
     * The creates an output data.
     * @param seed the seed to generate a random sequence of photos.
     * @param socket the socket to communicate with the server.
     */
    public MultiplayerOutputData(long seed, Socket socket) {
        this.seed = seed;
        this.socket = socket;
    }

    public long getSeed() {
        return seed;
    }

    public Socket getSocket() {
        return socket;
    }
}
