package entity;

/**
 * This presents a round of a game.
 */
public class GameRound {
    private final int photoID;
    private final double[] target;
    private final double[] chosen;
    private final boolean isAcceptable;

    /**
     * To create a round of game.
     *
     * @param photoID      the id of the photo
     * @param target       the coordinate of the target position
     * @param chosen       the coordinate of the chosen position
     * @param isAcceptable is the photo user chosen is acceptable
     */
    public GameRound(int photoID, double[] target, double[] chosen, boolean isAcceptable) {
        this.photoID = photoID;
        this.target = target;
        this.chosen = chosen;
        this.isAcceptable = isAcceptable;
    }

    /**
     * To get the chosen coordinate.
     *
     * @return the chosen coordinate
     */
    public double[] getChosen() {
        return chosen;
    }

    /**
     * To get the photo id.
     *
     * @return the photo id
     */
    public int getPhotoID() {
        return photoID;
    }

    /**
     * To get the target coordinate.
     *
     * @return the target coordinate
     */
    public double[] getTarget() {
        return target;
    }

    /**
     * To get if it is acceptable.
     *
     * @return is it acceptable
     */
    public boolean isAcceptable() {
        return isAcceptable;
    }
}
