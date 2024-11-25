package use_case.game;

import javax.swing.*;
import java.net.Socket;

/**
 * Data output object.
 */
public class GameOutputData {
    private boolean isAcceptable;

    private int score;

    private ImageIcon nextPhoto;

    private int photoID;

    private double[] target;

    private int round;

    /**
     * To create a game output data.
     * @param isAcceptable is acceptable
     * @param score score
     * @param nextPhoto next photo
     * @param photoID photo id
     * @param target the target coordinate
     * @param round the next round
     */
    public GameOutputData(boolean isAcceptable, int score, ImageIcon nextPhoto, int photoID,double[] target, int round) {
        this.isAcceptable = isAcceptable;
        this.score = score;
        this.nextPhoto = nextPhoto;
        this.round = round;
        this.target = target;
        this.photoID = photoID;
    }

    /**
     * Is acceptable.
     * @return isAcceptable
     */
    public boolean isAcceptable() {
        return isAcceptable;
    }

    /**
     * To get score.
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * To get the next photo.
     * @return photo
     */
    public ImageIcon getNextPhoto() {
        return nextPhoto;
    }

    /**
     * To get round.
     * @return round
     */
    public int getRound() {
        return round;
    }

    /**
     * To get the target coordinate.
     * @return the target coordinate
     */
    public double[] getTarget() {
        return target;
    }

    /**
     * To get the photo id.
     * @return photo id
     */
    public int getPhotoID() {
        return photoID;
    }

}
