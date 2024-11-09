package game;

import javax.swing.*;

public class PhotoLocation implements Location {

    private final ImageIcon photo;
    private final double[] location;

    public PhotoLocation(ImageIcon photo, double[] location) {
        this.photo = photo;
        this.location = location;
    }

    @Override
    public ImageIcon getPhoto() {
        return photo;
    }

    @Override
    public double[] getLocation() {
        return location;
    }
}
