package use_case.game;

import javax.swing.*;

public class GameOutputData {
    private boolean isAcceptable;

    private int score;

    private ImageIcon nextPhoto;

    private int photoID;

    private double[] target;

    private int round;

    public GameOutputData(boolean isAcceptable, int score, ImageIcon nextPhoto, int photoID,double[] target, int round) {
        this.isAcceptable = isAcceptable;
        this.score = score;
        this.nextPhoto = nextPhoto;
        this.round = round;
        this.target = target;
        this.photoID = photoID;
    }

    public boolean isAcceptable() {
        return isAcceptable;
    }

    public int getScore() {
        return score;
    }

    public ImageIcon getNextPhoto() {
        return nextPhoto;
    }

    public int getRound() {
        return round;
    }

    public double[] getTarget() {
        return target;
    }

    public int getPhotoID() {
        return photoID;
    }

}
