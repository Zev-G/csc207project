package use_case.multiplayer;

/**
 * This represents a input data.
 */
public class MultiplayerInputData {

    private final String username;

    private final String opponentUsername;

    /**
     * The input data.
     *
     * @param username         the username
     * @param opponentUsername the opponent username
     */
    public MultiplayerInputData(String username, String opponentUsername) {
        this.username = username;
        this.opponentUsername = opponentUsername;
    }

    /**
     * To get the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * To get the opponent username.
     *
     * @return opponent username
     */
    public String getOpponentUsername() {
        return opponentUsername;
    }
}
