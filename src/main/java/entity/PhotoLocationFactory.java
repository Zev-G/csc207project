package entity;

import use_case.dataAccessInterface.LocationDataAccess;

public class PhotoLocationFactory {

    private final LocationDataAccess dataAccess;

    public PhotoLocationFactory(LocationDataAccess data) {
        dataAccess = data;
    }

    public PhotoLocation getRandomLocation() {
        return dataAccess.getRandomLocation();
    }
}
