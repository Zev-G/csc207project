package entity;

public class PhotoLocation implements Location {

    private final String url;
    private final double[] location;

    public PhotoLocation(String url, double[] location) {
        this.url = url;
        this.location = location;
    }

    @Override
    public String getPhotoUrl() {
        return url;
    }

    @Override
    public double[] getLocation() {
        return location;
    }
}
