package interface_adapter.multiplayer;

import use_case.multiplayer.MultiplayerInputBoundary;
import use_case.multiplayer.MultiplayerInputData;

public class MultiplayerController {

    MultiplayerInputBoundary multiplayerInteractor;

    public MultiplayerController(MultiplayerInputBoundary multiplayerInteractor) {
        this.multiplayerInteractor = multiplayerInteractor;
    }

    public void execute(String username, String opponentUsername) {
        multiplayerInteractor.execute(new MultiplayerInputData(username, opponentUsername));
    }
}
