package use_case.mgame;

import use_case.game.GameOutputData;

import javax.swing.*;

/**
 * This is a Multiplayer Data output object.
 */
public class MGameOutputData extends GameOutputData {


    // The opponent score.
    private final int opponentScore;

    /**
     * This makes a game output data object for a multiplayer game.
     * @param isAcceptable is this game acceptable in the rang
     * @param score the score
     * @param nextPhoto the next photo
     * @param photoID the next photo id
     * @param target the target coordinate
     * @param round the next round
     * @param opponentScore the opponent score
     */
    public MGameOutputData(boolean isAcceptable, int score, ImageIcon nextPhoto, int photoID, double[] target, int round, int opponentScore) {
        super(isAcceptable, score, nextPhoto, photoID, target, round);
        this.opponentScore = opponentScore;
    }

    /**
     * To get the opponent score.
     * @return the opponent score
     */
    public int getOpponentScore() {
        return opponentScore;
    }
}
