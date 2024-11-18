package use_case.multiplayer;

public class MultiplayerInputData {

    private String username;

    private String opponentUsername;

    public MultiplayerInputData(String username, String opponentUsername) {
        this.username = username;
        this.opponentUsername = opponentUsername;
    }

    public String getUsername() {
        return username;
    }

    public String getOpponentUsername() {
        return opponentUsername;
    }
}
