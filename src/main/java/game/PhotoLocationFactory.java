package game;

public class PhotoLocationFactory {

    public static PhotoLocation getRandomLocation() {
        return new PhotoLocation("1", new double[]{1, 2});
    }
}
