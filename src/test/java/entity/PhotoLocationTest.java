package entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class PhotoLocationTest {

    static PhotoLocation photoLocation;

    @BeforeAll
    static void init() {
        ImageIcon photo1 = new ImageIcon(ClassLoader.getSystemResource("photos/sample1.jpg"));
        photoLocation = new PhotoLocation(photo1, new double[]{43.66742755781882, -79.39177102147445});
    }

    @Test
    void getPhoto() {
        assertEquals(photoLocation.getPhoto().getImage(),
                (new ImageIcon(ClassLoader.getSystemResource("photos/sample1.jpg"))).getImage());
    }

    @Test
    void getLocation() {
        assertEquals(photoLocation.getLocation()[0], 43.66742755781882, 0.0000000000001);
        assertEquals(photoLocation.getLocation()[1], -79.39177102147445, 0.0000000000001);
    }
}