package interface_adapter.stats;

import use_case.stats.UpdateStatsInputBoundary;
import use_case.stats.UpdateStatsInputData;

/**
 * Controller class responsible for updating user statistics.
 * Acts as a bridge between the user interface and the use case layer.
 */
public class UpdateStatsController {

    private final UpdateStatsInputBoundary interactor;

    /**
     * Constructs an UpdateStatsController with the specified interactor.
     *
     * @param interactor The interactor that handles the update stats use case.
     */
    public UpdateStatsController(UpdateStatsInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Updates the statistics of a user by delegating the task to the interactor.
     *
     * @param userName       The name of the user whose stats are being updated.
     * @param points         The number of points to be added to the user's stats.
     * @param correctGuesses The number of correct guesses to be added to the user's stats.
     */
    public void updateStats(String userName, int points, int correctGuesses) {
        // Create the input data
        UpdateStatsInputData inputData = new UpdateStatsInputData(userName, points, correctGuesses);

        // Call the interactor
        interactor.updateStats(inputData);
    }
}
