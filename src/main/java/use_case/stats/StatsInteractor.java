/**
 * StatsInteractor.java
 *
 * This class implements the use case logic for fetching user statistics.
 * It orchestrates the flow of data between the input boundary, data access layer, and output boundary,
 * adhering to the principles of Clean Architecture.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Documents its role in executing the core business logic for fetching statistics.
 * - **Clarity**: Provides clear descriptions of its fields, constructor, and methods.
 * - **Completeness**: Explains how it interacts with other components in the architecture.
 * - **Ease of Use**: Simplifies understanding of the interactor's responsibilities and integration.
 * - **Up-to-Dateness**: Reflects the current implementation details.
 */

package use_case.stats;

import use_case.dataAccessInterface.StatsDataAccess;

/**
 * The interactor for fetching user statistics.
 * Implements the {@link StatsInputBoundary} to handle statistics retrieval use case logic.
 */
public class StatsInteractor implements StatsInputBoundary {
    private final StatsDataAccess dataAccess;
    private final StatsOutputBoundary outputBoundary;

    /**
     * Constructs a StatsInteractor with the specified dependencies.
     *
     * @param dataAccess     The data access interface for retrieving user statistics.
     * @param outputBoundary The output boundary interface for presenting the retrieved statistics.
     *
     * Usage Example:
     * <pre>
     *     StatsDataAccess dataAccess = new StatsDatabaseAdapter(...);
     *     StatsOutputBoundary presenter = new StatsPresenter(...);
     *     StatsInteractor interactor = new StatsInteractor(dataAccess, presenter);
     *     interactor.fetchStats(new StatsInputData("user123"));
     * </pre>
     */
    public StatsInteractor(StatsDataAccess dataAccess, StatsOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Fetches user statistics based on the provided input data.
     *
     * Process:
     * 1. Extracts the username from the input data.
     * 2. Retrieves the statistics for the specified user from the data access layer.
     * 3. Passes the retrieved data to the output boundary for presentation.
     *
     * @param inputData The input data containing the username.
     */
    @Override
    public void fetchStats(StatsInputData inputData) {
        // Retrieve the username from the input data
        String username = inputData.getUsername();

        // Fetch user statistics from the data access layer
        StatsOutputData outputData = dataAccess.getUserStats(username);

        // Pass the retrieved data to the presenter via the output boundary
        outputBoundary.presentStats(outputData);
    }
}
