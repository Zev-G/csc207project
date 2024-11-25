package use_case.game;

/**
 * Game input data.
 */
public class GameInputData {

    private int photoID;

    private double[] target;

    private double[] chosen;

    /**
     * To create a game input data.
     * @param photoID the id of the photo
     * @param target the target coordinate
     * @param chosen the chosen coordinate
     */
    public GameInputData(int photoID, double[] target, double[] chosen) {
        this.photoID = photoID;
        this.target = target;
        this.chosen = chosen;
    }

    /**
     * TO get the chosen coordinate.
     * @return the chosen coordinate
     */
    public double[] getChosen() {
        return chosen;
    }

    /**
     * To get the photo ID.
     * @return the photo ID
     */
    public int getPhotoID() {
        return photoID;
    }

    /**
     * To get the target coordinate.
     * @return the target coordinate
     */
    public double[] getTarget() {
        return target;
    }
}
