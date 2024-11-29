/**
 * UpdateStatsController.java
 *
 * This class serves as the controller in the application’s architecture for updating statistics.
 * It acts as the intermediary between the user interface and the use case interactor.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Documents its role in preparing and passing input data.
 * - **Clarity**: Clearly explains the responsibilities of the controller.
 * - **Completeness**: Includes all methods and their purposes.
 * - **Ease of Use**: Facilitates integration with user interface components.
 * - **Up-to-Dateness**: Reflects the latest method signature and behavior.
 */

package interface_adapter.stats;

import use_case.stats.UpdateStatsInputBoundary;
import use_case.stats.UpdateStatsInputData;

/**
 * Controller for updating user statistics.
 * Responsible for handling user requests and invoking the update stats use case.
 */
public class UpdateStatsController {

    private final UpdateStatsInputBoundary interactor;

    /**
     * Constructs an UpdateStatsController with the given interactor.
     *
     * @param interactor The interactor responsible for processing update stats requests.
     */
    public UpdateStatsController(UpdateStatsInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Updates user statistics by preparing input data and delegating the request to the interactor.
     *
     * @param userName       The username of the user.
     * @param points         The points to update.
     * @param correctGuesses The number of correct guesses to update.
     *
     * Usage Example:
     * <pre>
     *     UpdateStatsController controller = new UpdateStatsController(interactor);
     *     controller.updateStats("user123", 100, 10);
     * </pre>
     */
    public void updateStats(String userName, int points, int correctGuesses) {
        // Create the input data
        UpdateStatsInputData inputData = new UpdateStatsInputData(userName, points, correctGuesses);

        // Call the interactor
        interactor.updateStats(inputData);
    }
}

