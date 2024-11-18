package use_case.multiplayer;

import java.net.Socket;

public class MultiplayerOutputData {

    private final long seed;

    private final Socket socket;

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
