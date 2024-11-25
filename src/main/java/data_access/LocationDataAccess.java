package data_access;

import entity.PhotoLocation;

public interface LocationDataAccess {

    int getPhotoID();

    void setSeed(long seed);
    PhotoLocation getRandomLocation();
}
