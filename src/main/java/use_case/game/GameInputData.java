package use_case.game;

public class GameInputData {

    private int photoID;

    private double[] target;

    private double[] chosen;

    public GameInputData(int photoID, double[] target, double[] chosen) {
        this.photoID = photoID;
        this.target = target;
        this.chosen = chosen;
    }

    public double[] getChosen() {
        return chosen;
    }

    public int getPhotoID() {
        return photoID;
    }

    public double[] getTarget() {
        return target;
    }
}
