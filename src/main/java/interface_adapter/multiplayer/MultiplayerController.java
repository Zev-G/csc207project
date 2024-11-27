package interface_adapter.multiplayer;

import use_case.multiplayer.MultiplayerInputBoundary;
import use_case.multiplayer.MultiplayerInputData;

/**
 * A multiplayer controller.
 */
public class MultiplayerController {

    MultiplayerInputBoundary multiplayerInteractor;

    /**
     * To create a multiplayer controller.
     * @param multiplayerInteractor multiplayer interactor
     */
    public MultiplayerController(MultiplayerInputBoundary multiplayerInteractor) {
        this.multiplayerInteractor = multiplayerInteractor;
    }

    /**
     * To start a new multiplayer game.
     *
     * @param username         the current username
     * @param opponentUsername the opponent username
     */
    public void execute(String username, String opponentUsername) {
        multiplayerInteractor.execute(new MultiplayerInputData(username, opponentUsername));
    }
}
