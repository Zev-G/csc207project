package use_case.stats;

/**
 * Defines the input boundary for fetching user statistics.
 */
public interface StatsInputBoundary {

    /**
     * Fetches user statistics based on the provided input data.
     *
     * @param inputData the input data containing details required to fetch statistics
     */
    void fetchStats(StatsInputData inputData);
}
