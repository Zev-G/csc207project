package interface_adapter.stats;

import use_case.stats.StatsInputBoundary;
import use_case.stats.StatsInputData;

public class StatsController {
    private final StatsInputBoundary interactor; // Reference to the interactor

    public StatsController(StatsInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void fetchStats(String username) {
        // Create the input data
        StatsInputData inputData = new StatsInputData(username);

        // Pass the input data to the interactor
        interactor.fetchStats(inputData);
    }
}
