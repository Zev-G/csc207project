package data_access;

import entity.PhotoLocation;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class DataAccessMock implements LocationDataAccess {

    private final ArrayList<PhotoLocation> locations = new ArrayList<>();

    private Random random;

    public DataAccessMock(long seed) {
        random = new Random(seed);
        ImageIcon photo1 = new ImageIcon(ClassLoader.getSystemResource("photos/sample1.jpg"));
        PhotoLocation photoLocation1 = new PhotoLocation(photo1, new double[]{43.66742755781882, -79.39177102147445}, 1);
        ImageIcon photo2 = new ImageIcon(ClassLoader.getSystemResource("photos/sample2.jpeg"));
        PhotoLocation photoLocation2 = new PhotoLocation(photo2, new double[]{43.65984277958618, -79.39718377820866}, 2);
        locations.add(photoLocation1);
        locations.add(photoLocation2);
    }

    public DataAccessMock() {
        random = new Random();
        ImageIcon photo1 = new ImageIcon(ClassLoader.getSystemResource("photos/sample1.jpg"));
        PhotoLocation photoLocation1 = new PhotoLocation(photo1, new double[]{43.66742755781882, -79.39177102147445}, 1);
        ImageIcon photo2 = new ImageIcon(ClassLoader.getSystemResource("photos/sample2.jpeg"));
        PhotoLocation photoLocation2 = new PhotoLocation(photo2, new double[]{43.65984277958618, -79.39718377820866}, 2);
        locations.add(photoLocation1);
        locations.add(photoLocation2);
    }

    @Override
    public int getPhotoID() {
        return 0;
    }

    @Override
    public void setSeed(long seed) {
        random = new Random(seed);
    }

    @Override
    public PhotoLocation getRandomLocation() {
        switch (random.nextInt(2)) {
            case 0:
                return locations.get(0);
            default:
                return locations.get(1);
        }
    }
}
