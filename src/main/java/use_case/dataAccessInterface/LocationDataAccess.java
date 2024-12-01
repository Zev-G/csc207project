package use_case.dataAccessInterface;

import entity.PhotoLocation;

/**
 * Interface for accessing location data, including retrieving random locations
 * and fetching locations by photo ID.
 */
public interface LocationDataAccess {

    /**
     * Retrieves the ID of a photo.
     *
     * @return the photo ID
     */
    int getPhotoID();

    /**
     * Retrieves the location associated with a specific photo ID.
     *
     * @param id the photo ID
     * @return the photo location
     */
    PhotoLocation getPhotoLocationByID(int id);

    /**
     * Sets the seed for generating random locations.
     *
     * @param seed the seed value
     */
    void setSeed(long seed);

    /**
     * Retrieves a random location.
     *
     * @return a random photo location
     */
    PhotoLocation getRandomLocation();
}
