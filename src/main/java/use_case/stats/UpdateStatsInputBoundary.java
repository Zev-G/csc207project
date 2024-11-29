/**
 * UpdateStatsInputBoundary.java
 *
 * This interface defines the input boundary for the update statistics use case in the application.
 * It adheres to Clean Architecture principles by decoupling the use case logic from the controller.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Clearly describes its role as the entry point for updating statistics.
 * - **Clarity**: Provides a concise and unambiguous definition of its responsibilities.
 * - **Completeness**: Covers the single method and its purpose.
 * - **Ease of Use**: Simplifies integration with controllers by defining a straightforward contract.
 * - **Up-to-Dateness**: Reflects the current method signature and expected behavior.
 */

package use_case.stats;

/**
 * Input boundary for the update statistics use case.
 * Defines the contract for updating user statistics.
 */
public interface UpdateStatsInputBoundary {

    /**
     * Updates user statistics based on the provided request model.
     *
     * @param requestModel The input data model containing the user's details and statistics to update.
     *
     * Responsibilities:
     * - Accepts an input model with data for updating user statistics.
     * - Delegates the update operation to the implementing class.
     *
     * Usage Example:
     * <pre>
     *     UpdateStatsInputBoundary interactor = new UpdateStatsInteractor(...);
     *     interactor.updateStats(new UpdateStatsInputData("user123", 50, 10));
     * </pre>
     */
    void updateStats(UpdateStatsInputData requestModel);
}
