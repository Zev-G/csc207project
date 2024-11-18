package use_case.multiplayer;

/**
 * This represents a multiplayer game input boundary.
 */
public interface MultiplayerInputBoundary {
    /**
     * Executes the multiplayer.
     *
     * @param multiplayerInputData the input data
     */
    void execute(MultiplayerInputData multiplayerInputData);
}
