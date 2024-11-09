package game;

import javax.swing.*;
import java.util.ArrayList;

public class DataAccessMock implements LocationDataAccess {

    private final ArrayList<PhotoLocation> locations = new ArrayList<>();

    public DataAccessMock() {
        ImageIcon photo1 = new ImageIcon(ClassLoader.getSystemResource("photos/sample1.jpg"));
        PhotoLocation photoLocation1 = new PhotoLocation(photo1, new double[]{43.66742755781882, -79.39177102147445});
        ImageIcon photo2 = new ImageIcon(ClassLoader.getSystemResource("photos/sample2.jpeg"));
        PhotoLocation photoLocation2 = new PhotoLocation(photo1, new double[]{43.65984277958618, -79.39718377820866});
        locations.add(photoLocation1);
        locations.add(photoLocation2);
    }

    @Override
    public PhotoLocation getRamdonLocation() {
        if(Math.random()<.5){
            return locations.get(0);
        }else {
            return locations.get(1);
        }
    }
}
