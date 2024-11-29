package interface_adapter.game;

import javax.swing.*;

/**
 * The GameState class represents the current state of the game.
 */
public class GameState {

    private boolean isAcceptable;
    private int score;
    private ImageIcon nextPhoto;
    private double[] target;
    private int round;
    private int photoID;
    private boolean gameOver;

    /**
     * Constructs a new GameState with default values.
     * Initializes isAcceptable to false, score to 0, round to 1, nextPhoto to null, and photoID to 0.
     */
    public GameState() {
        isAcceptable = false;
        score = 0;
        round = 1;
        nextPhoto = null;
        photoID = 0;
    }

    /**
     * Constructs a new GameState with specified values.
     *
     * @param isAcceptable whether the current state is acceptable
     * @param score        the current score
     * @param nextPhoto    the ImageIcon for the next photo
     * @param photoID      the ID of the current photo
     * @param target       the target values as a double array
     * @param round        the current round number
     */
    public GameState(boolean isAcceptable, int score, ImageIcon nextPhoto, int photoID, double[] target, int round) {
        this.isAcceptable = isAcceptable;
        this.score = score;
        this.nextPhoto = nextPhoto;
        this.round = round;
        this.target = target;
        this.photoID = photoID;
    }

    /**
     * Constructs a new GameState with specified values including game over status.
     *
     * @param isAcceptable whether the current state is acceptable
     * @param score        the current score
     * @param nextPhoto    the ImageIcon for the next photo
     * @param photoID      the ID of the current photo
     * @param target       the target values as a double array
     * @param round        the current round number
     * @param gameOver     whether the game is over
     */
    public GameState(boolean isAcceptable, int score, ImageIcon nextPhoto, int photoID, double[] target, int round, boolean gameOver) {
        this.isAcceptable = isAcceptable;
        this.score = score;
        this.nextPhoto = nextPhoto;
        this.round = round;
        this.target = target;
        this.photoID = photoID;
        this.gameOver = gameOver;
    }

    /**
     * Checks if the current state is acceptable.
     *
     * @return true if the state is acceptable, false otherwise
     */
    public boolean isAcceptable() {
        return isAcceptable;
    }

    /**
     * Sets the acceptable status of the current state.
     *
     * @param acceptable the new acceptable status
     */
    public void setAcceptable(boolean acceptable) {
        isAcceptable = acceptable;
    }

    /**
     * Gets the current score.
     *
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the current score.
     *
     * @param score the new score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets the ImageIcon for the next photo.
     *
     * @return the ImageIcon for the next photo
     */
    public ImageIcon getNextPhoto() {
        return nextPhoto;
    }

    /**
     * Sets the ImageIcon for the next photo.
     *
     * @param nextPhoto the new ImageIcon for the next photo
     */
    public void setNextPhoto(ImageIcon nextPhoto) {
        this.nextPhoto = nextPhoto;
    }

    /**
     * Gets the current round number.
     *
     * @return the current round number
     */
    public int getRound() {
        return round;
    }

    /**
     * Sets the current round number.
     *
     * @param round the new round number
     */
    public void setRound(int round) {
        this.round = round;
    }

    /**
     * Gets the target values.
     *
     * @return the target values as a double array
     */
    public double[] getTarget() {
        return target;
    }

    /**
     * Sets the target values.
     *
     * @param target the new target values as a double array
     */
    public void setTarget(double[] target) {
        this.target = target;
    }

    /**
     * Gets the ID of the current photo.
     *
     * @return the ID of the current photo
     */
    public int getPhotoID() {
        return photoID;
    }

    /**
     * Sets the ID of the current photo.
     *
     * @param photoID the new ID of the current photo
     */
    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    /**
     * Checks if the current state should be shown.
     *
     * @return true if the current round is 11, false otherwise
     */
    public boolean shouldShow() {
        return this.round == 11;
    }

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Sets the game over status.
     *
     * @param gameOver the new game over status
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
