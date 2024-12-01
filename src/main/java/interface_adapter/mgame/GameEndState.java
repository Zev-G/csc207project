package interface_adapter.mgame;

import interface_adapter.game.GameState;

/**
 * Represents the final state of a game, storing the scores of the user and the opponent.
 */
public class GameEndState {

    private int user; // Score of the user
    private int opp;  // Score of the opponent

    /**
     * Constructs a GameEndState with the given scores.
     *
     * @param user the score of the user
     * @param opp  the score of the opponent
     */
    public GameEndState(int user, int opp) {
        this.user = user;
        this.opp = opp;
    }

    /**
     * Retrieves the user's score.
     *
     * @return the user's score
     */
    public int getUser() {
        return user;
    }

    /**
     * Retrieves the opponent's score.
     *
     * @return the opponent's score
     */
    public int getOpp() {
        return opp;
    }
}
