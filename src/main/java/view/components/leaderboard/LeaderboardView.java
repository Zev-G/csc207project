package view.components.leaderboard;

import entity.DummyUserStats;
import interface_adapter.ViewModel;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import view.View;
import view.components.standard.VerticalPanel;

/**
 * A view component for displaying the leaderboard, showing user positions and their statistics.
 */
public class LeaderboardView extends VerticalPanel implements View<LeaderboardState> {

    private final LeaderboardViewModel viewModel;

    /**
     * Constructs a LeaderboardView with the given view model.
     *
     * @param viewModel the view model providing leaderboard data
     */
    public LeaderboardView(LeaderboardViewModel viewModel) {
        this.viewModel = viewModel;

        setAlignmentX(LEFT_ALIGNMENT);

        loadCurrentState();
        viewModel.addPropertyChangeListener(evt -> loadCurrentState());
    }

    /**
     * Updates the leaderboard view with the given state.
     *
     * @param state the state containing leaderboard positions and user statistics
     */
    @Override
    public void loadState(LeaderboardState state) {
        removeAll();
        for (int pos = 2; pos >= 0; pos--) { // Top 3 positions in reverse order
            DummyUserStats userStats = state.getPosition(pos);
            add(new LeaderboardPositionView(userStats));
        }
    }

    /**
     * Returns the view model associated with this leaderboard view.
     *
     * @return the view model for the leaderboard
     */
    @Override
    public ViewModel<LeaderboardState> getViewModel() {
        return viewModel;
    }
}
