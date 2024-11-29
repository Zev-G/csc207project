package use_case.game;

/**
 * Defines the input boundary for fetching game summary statistics.
 */
public interface GameSummaryInputBoundary {

    /**
     * Fetches game statistics based on the provided input data.
     *
     * @param inputData the input data containing game details for fetching stats
     */
    void fetchStats(GameSummaryInputData inputData);
}
