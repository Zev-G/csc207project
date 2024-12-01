package interface_adapter.game;

import use_case.game.GameSummaryOutputBoundary;
import use_case.game.GameSummaryOutputData;

/**
 * Presenter for game summaries, updating the ViewModel with transformed data.
 */
public class GameSummaryPresenter implements GameSummaryOutputBoundary {

    private final GameSummaryPageViewModel viewModel;

    /**
     * Initializes the presenter with the given ViewModel.
     *
     * @param viewModel the ViewModel to update with state.
     */
    public GameSummaryPresenter(GameSummaryPageViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Transforms the output data into a page state and updates the ViewModel.
     *
     * @param outputData the data to transform and present.
     */
    @Override
    public void presentStats(GameSummaryOutputData outputData) {
        GameSummaryPageState state = GameSummaryPageState.fromStatsOutputData(outputData);
        viewModel.setState(state);
    }
}
