package interface_adapter.stats;

import use_case.stats.StatsOutputBoundary;
import use_case.stats.StatsOutputData;

/**
 * Presenter class that transforms use case output data into a view model state.
 * This class acts as a bridge between the application's use case layer and the UI layer.
 */
public class StatsPresenter implements StatsOutputBoundary {

    private final StatsPageViewModel viewModel;

    /**
     * Constructs a StatsPresenter with the given ViewModel.
     *
     * @param viewModel The ViewModel to update with new stats state.
     */
    public StatsPresenter(StatsPageViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Transforms the provided output data into a view model state and updates the ViewModel.
     *
     * @param outputData The stats data produced by the use case layer.
     */
    @Override
    public void presentStats(StatsOutputData outputData) {
        // Create a new StatsPageState using the data from the use case layer
        StatsPageState state = StatsPageState.fromStatsOutputData(outputData);

        // Update the ViewModel with the newly created state
        viewModel.setState(state);
    }
}
