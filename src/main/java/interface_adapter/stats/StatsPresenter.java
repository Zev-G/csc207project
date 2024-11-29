/**
 * StatsPresenter.java
 *
 * This class acts as the presenter in the application’s Clean Architecture structure.
 * It converts the output data from the use case layer into a format suitable for the view model.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Clearly defines its role in updating the view model.
 * - **Clarity**: Provides concise and focused descriptions of responsibilities and behavior.
 * - **Completeness**: Documents the transformation process of output data to view model state.
 * - **Ease of Use**: Demonstrates integration with the `StatsPageViewModel`.
 * - **Up-to-Dateness**: Reflects the current behavior of the `presentStats` method.
 */

package interface_adapter.stats;

import use_case.stats.StatsOutputBoundary;
import use_case.stats.StatsOutputData;

/**
 * The presenter for user statistics.
 * Converts output data from the use case layer into a view model state.
 */
public class StatsPresenter implements StatsOutputBoundary {

    private final StatsPageViewModel viewModel;

    /**
     * Constructs a StatsPresenter with the given view model.
     *
     * @param viewModel The ViewModel responsible for representing the statistics page state.
     */
    public StatsPresenter(StatsPageViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Transforms StatsOutputData into StatsPageState and updates the view model.
     *
     * @param outputData The data to present, containing user statistics.
     *
     * Usage Example:
     * <pre>
     *     StatsPageViewModel viewModel = new StatsPageViewModel();
     *     StatsPresenter presenter = new StatsPresenter(viewModel);
     *     presenter.presentStats(new StatsOutputData("user123", 100, 5, 20));
     * </pre>
     */
    @Override
    public void presentStats(StatsOutputData outputData) {
        // Create a new StatsPageState using the factory method
        StatsPageState state = StatsPageState.fromStatsOutputData(outputData);

        // Update the ViewModel with the new state
        viewModel.setState(state);
    }
}
