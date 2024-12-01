package interface_adapter.stats;

import use_case.stats.StatsInputBoundary;
import use_case.stats.StatsInputData;
import use_case.stats.StatsInteractor;

/**
 * Controller for handling statistics-related operations.
 * Acts as a bridge between the user interface and the use case layer.
 */
public class StatsController {

    private final StatsInteractor interactor; // Reference to the interactor

    /**
     * Constructs a StatsController with the specified interactor.
     *
     * @param interactor the interactor to handle statistics logic
     */
    public StatsController(StatsInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Fetches statistics for a given username.
     *
     * @param username the username for which statistics are to be fetched
     */
    public void fetchStats(String username) {
        // Create the input data
        StatsInputData inputData = new StatsInputData(username);

        // Pass the input data to the interactor
        interactor.fetchStats(inputData);
    }
}
