/**
 * StatsInputBoundary.java
 *
 * This interface defines the input boundary for the statistics use case in the application.
 * It serves as the entry point for requests to fetch user statistics, adhering to the principles
 * of Clean Architecture by decoupling the use case from the user interface.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Describes the role of the interface as an entry point for statistics-related requests.
 * - **Clarity**: Clearly defines the contract for fetching statistics.
 * - **Completeness**: Covers the primary method and its purpose.
 * - **Ease of Use**: Provides a straightforward interface for implementing use case logic.
 * - **Up-to-Dateness**: Reflects the current method signature and intended use.
 */

package use_case.stats;

/**
 * Input boundary for the statistics use case.
 * Provides an interface for initiating requests to fetch user statistics.
 * Implementations of this interface handle the core business logic for statistics retrieval.
 *
 * Responsibilities:
 * - Accepts input data containing user-specific details.
 * - Delegates the responsibility of fetching and processing statistics to the implementing class.
 *
 * Usage Example:
 * <pre>
 *     StatsInputBoundary statsBoundary = new StatsInteractor(...);
 *     statsBoundary.fetchStats(new StatsInputData("user123"));
 * </pre>
 */
public interface StatsInputBoundary {

    /**
     * Fetches statistics for the user specified in the input data.
     *
     * @param inputData An instance of {@link StatsInputData} containing the username and related details.
     */
    void fetchStats(StatsInputData inputData);
}
