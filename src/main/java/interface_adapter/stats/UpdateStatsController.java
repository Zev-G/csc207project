package interface_adapter.stats;

import use_case.stats.UpdateStatsInputBoundary;
import use_case.stats.UpdateStatsInputData;

public class UpdateStatsController {

    private final UpdateStatsInputBoundary interactor;

    public UpdateStatsController(UpdateStatsInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void updateStats(String userName, int points, int correctGuesses) {
        // Create the input data
        UpdateStatsInputData inputData = new UpdateStatsInputData(userName, points, correctGuesses);

        // Call the interactor
        interactor.updateStats(inputData);
    }
}