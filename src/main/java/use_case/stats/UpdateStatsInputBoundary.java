package use_case.stats;

/**
 * Defines the input boundary for updating user statistics.
 */
public interface UpdateStatsInputBoundary {

    /**
     * Updates user statistics based on the provided request model.
     *
     * @param requestModel the data containing the statistics update details
     */
    void updateStats(UpdateStatsInputData requestModel);
}
