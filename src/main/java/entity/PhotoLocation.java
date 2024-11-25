package entity;

import javax.swing.*;

public class PhotoLocation implements Location {

    private final ImageIcon photo;
    private final double[] location;

    private final int photoID;

    public PhotoLocation(ImageIcon photo, double[] location, int photoID) {
        this.photo = photo;
        this.location = location;
        this.photoID = photoID;
    }

    @Override
    public ImageIcon getPhoto() {
        return photo;
    }

    @Override
    public double[] getLocation() {
        return location;
    }

    @Override
    public int getPhotoID() {
        return photoID;
    }
}
