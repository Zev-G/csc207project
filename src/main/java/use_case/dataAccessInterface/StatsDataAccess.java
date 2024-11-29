package use_case.dataAccessInterface;

import use_case.stats.StatsOutputData;

/**
 * Interface for accessing user statistics data.
 */
public interface StatsDataAccess {

    /**
     * Retrieves the statistics associated with a specific username.
     *
     * @param username the username of the user
     * @return the statistics output data for the user
     */
    StatsOutputData getUserStats(String username);
}
