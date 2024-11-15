package view.components.leaderboard;

import entity.DummyUserStats;
import interface_adapter.ViewModel;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import view.View;
import view.components.standard.VerticalPanel;

public class LeaderboardView extends VerticalPanel implements View<LeaderboardState> {

    private final LeaderboardViewModel viewModel;



    public LeaderboardView(LeaderboardViewModel viewModel) {
        this.viewModel = viewModel;

        setAlignmentX(LEFT_ALIGNMENT);

        loadCurrentState();
        viewModel.addPropertyChangeListener(evt -> loadCurrentState());
    }


    @Override
    public void loadState(LeaderboardState state) {
        removeAll();
        for (int pos = 2; pos >= 0; pos--) {
            DummyUserStats userStats = state.getPosition(pos);
            add(new LeaderboardPositionView(userStats));
        }
    }

    @Override
    public ViewModel<LeaderboardState> getViewModel() {
        return viewModel;
    }
}
