package interface_adapter.game;

import use_case.game.GameSummaryOutputBoundary;
import use_case.game.GameSummaryOutputData;

public class GameSummaryPresenter implements GameSummaryOutputBoundary {

    private final GameSummaryPageViewModel viewModel;

    public GameSummaryPresenter(GameSummaryPageViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Takes StatsOutputData and directly transforms it into StatsPageState,
     * then updates the ViewModel with the new state.
     *
     * @param outputData The data to present.
     */
    @Override
    public void presentStats(GameSummaryOutputData outputData) {
        // Create a new StatsPageState using the factory method
        GameSummaryPageState state = GameSummaryPageState.fromStatsOutputData(outputData);

        // Update the ViewModel with the new state
        viewModel.setState(state);
    }
}
