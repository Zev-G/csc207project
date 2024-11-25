package use_case.mgame;

import use_case.game.GameInputBoundary;

import java.net.Socket;

public interface MGameInputBoundary extends GameInputBoundary {

    void startMGame(long seed, Socket socket);
}
