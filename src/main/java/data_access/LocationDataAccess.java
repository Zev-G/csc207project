package data_access;

import entity.PhotoLocation;

/**
 * This is the interface to get a random location from a database.
 */
public interface LocationDataAccess {

    /**
     * To get a photo from a random place.
     * @return
     */

    int getPhotoID();

    void setSeed(long seed);
    PhotoLocation getRandomLocation();
}
