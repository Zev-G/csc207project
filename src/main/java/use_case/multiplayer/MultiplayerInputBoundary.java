package use_case.multiplayer;

import java.io.IOException;

/**
 * This represents a multiplayer game input boundary.
 */
public interface MultiplayerInputBoundary {
    /**
     * Executes the multiplayer.
     *
     * @param multiplayerInputData the input data
     * @throws IOException may throw IO exception
     */
    void execute(MultiplayerInputData multiplayerInputData);
}
