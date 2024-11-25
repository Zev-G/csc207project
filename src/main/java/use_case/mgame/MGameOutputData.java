package use_case.mgame;

import use_case.game.GameOutputData;

import javax.swing.*;

public class MGameOutputData extends GameOutputData {


    private int opponentScore;

    public MGameOutputData(boolean isAcceptable, int score, ImageIcon nextPhoto, int photoID, double[] target, int round, int opponentScore) {
        super(isAcceptable, score, nextPhoto, photoID, target, round);
        this.opponentScore = opponentScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }
}
