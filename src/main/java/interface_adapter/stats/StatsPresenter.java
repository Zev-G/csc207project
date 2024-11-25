package interface_adapter.stats;

import use_case.stats.StatsOutputData;

public class StatsPresenter {

    private final StatsPageViewModel viewModel;

    public StatsPresenter(StatsPageViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Takes StatsOutputData and directly transforms it into StatsPageState,
     * then updates the ViewModel with the new state.
     *
     * @param outputData The data to present.
     */
    public void present(StatsOutputData outputData) {
        // Create a new StatsPageState using the factory method
        StatsPageState state = StatsPageState.fromStatsOutputData(outputData);

        // Update the ViewModel with the new state
        viewModel.setState(state);
    }
}
