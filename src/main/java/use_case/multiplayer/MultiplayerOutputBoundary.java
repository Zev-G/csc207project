package use_case.multiplayer;

import java.io.IOException;

public interface MultiplayerOutputBoundary {

    void prepareTimeoutView();

    void prepareGame(MultiplayerOutputData multiplayerOutputData);
}
