package interface_adapter.stats;

import use_case.stats.StatsInputBoundary;
import use_case.stats.StatsInputData;
import use_case.stats.StatsInteractor;

public class StatsController {
    private final StatsInteractor interactor; // Reference to the interactor

    public StatsController(StatsInteractor interactor) {
        this.interactor = interactor;
    }

    public void fetchStats(String username) {
        // Create the input data
        StatsInputData inputData = new StatsInputData(username);

        // Pass the input data to the interactor
        interactor.fetchStats(inputData);
    }
}
