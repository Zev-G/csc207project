package data_access;

import entity.PhotoLocation;

/**
 * This is the interface to get a random location from a database.
 */
public interface LocationDataAccess {

    int getPhotoID();

    PhotoLocation getPhotoLocationByID(int id);

    void setSeed(long seed);

    PhotoLocation getRandomLocation();
}
