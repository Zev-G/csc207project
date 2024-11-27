package interface_adapter.game;

import javax.swing.*;

public class GameState {

    private boolean isAcceptable;

    private int score;

    private ImageIcon nextPhoto;

    private double[] target;

    private int round;

    private int photoID;

    public GameState() {
        isAcceptable = false;
        score = 0;
        round = 1;
        nextPhoto = null;
        photoID = 0;
    }

    public GameState(boolean isAcceptable, int score, ImageIcon nextPhoto, int photoID, double[] target, int round) {
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

    public void setAcceptable(boolean acceptable) {
        isAcceptable = acceptable;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ImageIcon getNextPhoto() {
        return nextPhoto;
    }

    public void setNextPhoto(ImageIcon nextPhoto) {
        this.nextPhoto = nextPhoto;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public double[] getTarget() {
        return target;
    }

    public void setTarget(double[] target) {
        this.target = target;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public boolean shouldShow(){ return this.round == 11; }
}
