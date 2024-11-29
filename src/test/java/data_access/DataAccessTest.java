package data_access;

import entity.PhotoLocation;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class DataAccessTest {

    @Test
    void testFetchImagesAndLocations() {
        // Initialize DataAccess with a fixed seed for reproducibility
        DataAccess dataAccess = new DataAccess(12345L);

        // Ensure images and locations are fetched successfully
        assertFalse(dataAccess.getLocations().isEmpty(), "Locations list should not be empty.");

        // Test the first PhotoLocation object
        PhotoLocation photoLocation = dataAccess.getLocations().get(0);

        assertNotNull(photoLocation, "PhotoLocation should not be null.");
        assertNotNull(photoLocation.getPhoto(), "ImageIcon in PhotoLocation should not be null.");
        assertNotNull(photoLocation.getLocation(), "Coordinates in PhotoLocation should not be null.");

        // Check that the coordinates have two values (latitude and longitude)
        double[] coordinates = photoLocation.getLocation();
        assertEquals(2, coordinates.length, "Coordinates should have exactly two values (latitude and longitude).");

        // Ensure the image is correctly fetched
        ImageIcon photo = photoLocation.getPhoto();
        assertTrue(photo.getIconWidth() > 0 && photo.getIconHeight() > 0, "ImageIcon should have valid dimensions.");
    }

    @Test
    void testGetRandomLocation() {
        // Initialize DataAccess with a fixed seed
        DataAccess dataAccess = new DataAccess(12345L);

        // Fetch a random PhotoLocation
        PhotoLocation randomLocation = dataAccess.getRandomLocation();

        assertNotNull(randomLocation, "Random PhotoLocation should not be null.");
        assertNotNull(randomLocation.getPhoto(), "ImageIcon in random PhotoLocation should not be null.");
        assertNotNull(randomLocation.getLocation(), "Coordinates in random PhotoLocation should not be null.");
    }
}
