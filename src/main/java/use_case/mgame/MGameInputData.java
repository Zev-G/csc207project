package use_case.mgame;

import use_case.game.GameInputData;

import java.net.Socket;

public class MGameInputData extends GameInputData {

    private long seed;

    private Socket socket;

    public MGameInputData(int photoID, double[] target, double[] chosen) {
        super(photoID, target, chosen);
    }

    public MGameInputData(int photoID, double[] target, double[] chosen, long seed, Socket socket) {
        super(photoID, target, chosen);
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
