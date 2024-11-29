/**
 * StatsController.java
 *
 * This class serves as the controller in the application’s architecture, facilitating the interaction between
 * the user interface and the stats use case. It handles requests for fetching user statistics and
 * delegates them to the appropriate interactor.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Describes the exact role of this class in the system.
 * - **Clarity**: Uses straightforward and unambiguous language.
 * - **Completeness**: Provides detailed documentation for all elements of the class.
 * - **Ease of Use**: Explains functionality in a manner that facilitates easy integration.
 * - **Up-to-Dateness**: Includes current descriptions of relationships with other components.
 */

package interface_adapter.stats;

import use_case.stats.StatsInputData;
import use_case.stats.StatsInteractor;


/**
 * Controller class for handling requests related to statistics.
 *
 * Responsibilities:
 * - Accepts a username from the caller.
 * - Prepares and packages the input data required for the stats use case.
 * - Delegates the request to the StatsInteractor for processing.
 */
public class StatsController {
    /**
     * Interactor responsible for processing stats-related requests.
     * Acts as the use case handler for fetching user statistics.
     */
    private final StatsInteractor interactor; // Reference to the interactor

    /**
     * Constructor for StatsController.
     *
     * @param interactor The StatsInteractor instance to which this controller delegates requests.
     */
    public StatsController(StatsInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Fetches statistics for a given username.
     *
     * Process:
     * 1. Creates an instance of StatsInputData using the provided username.
     * 2. Passes the StatsInputData object to the StatsInteractor for processing.
     *
     * @param username The username for which statistics are to be fetched.
     *
     * Usage Example:
     * <pre>
     *     StatsController controller = new StatsController(interactor);
     *     controller.fetchStats("test_user");
     * </pre>
     */
    public void fetchStats(String username) {
        // Create the input data
        StatsInputData inputData = new StatsInputData(username);

        // Pass the input data to the interactor
        interactor.fetchStats(inputData);
    }
}
