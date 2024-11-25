package entity;

public class GameRound {
    private int photoID;

    private double[] target;

    private double[] chosen;

    private boolean isAcceptable;

    public GameRound(int photoID, double[] target, double[] chosen, boolean isAcceptable){
        this.photoID = photoID;
        this.target = target;
        this.chosen = chosen;
        this.isAcceptable = isAcceptable;
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

    public boolean isAcceptable() {
        return isAcceptable;
    }
}
