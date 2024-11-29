package use_case.stats;

/**
 * Defines the output boundary for presenting user statistics.
 */
public interface StatsOutputBoundary {

    /**
     * Presents the user statistics.
     *
     * @param outputData the data containing user statistics to be presented
     */
    void presentStats(StatsOutputData outputData);
}
