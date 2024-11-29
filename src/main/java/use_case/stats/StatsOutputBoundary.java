/**
 * StatsOutputBoundary.java
 *
 * This interface defines the output boundary for the statistics use case in the application.
 * It adheres to Clean Architecture principles by decoupling the use case logic from the presentation layer.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Clearly defines its role in passing output data for presentation.
 * - **Clarity**: Provides a concise description of the contract for presenting statistics.
 * - **Completeness**: Covers the single method and its expected behavior.
 * - **Ease of Use**: Ensures straightforward implementation for presenters.
 * - **Up-to-Dateness**: Reflects the current requirements for the output boundary.
 */

package use_case.stats;

/**
 * Output boundary for the statistics use case.
 * This interface defines the contract for presenting statistics data fetched by the interactor.
 * Implementations of this interface typically adapt the data into a form suitable for display.
 *
 * Responsibilities:
 * - Receives processed statistics data from the use case interactor.
 * - Passes the data to a presenter or view model for rendering or further processing.
 *
 * Usage Example:
 * <pre>
 *     StatsOutputBoundary presenter = new StatsPresenter(viewModel);
 *     presenter.presentStats(new StatsOutputData("user123", 100, 5, 20));
 * </pre>
 */
public interface StatsOutputBoundary {

    /**
     * Presents the statistics data fetched from the use case.
     *
     * @param outputData The data to present, containing user statistics like points, games played, and correct guesses.
     */
    void presentStats(StatsOutputData outputData);
}
