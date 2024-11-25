package view.app;

import data_access.DataAccessMock;
import entity.DummyUserStats;
import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import interface_adapter.game.GameController;
import interface_adapter.game.GamePresenter;
import interface_adapter.game.GameViewModel;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.stats.StatsPageViewModel;
import use_case.game.GameInteractor;
import view.pages.ViewManager;

import javax.swing.*;
import java.util.List;

public class App {

    // Models
    private final AccountViewModel accountViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LeaderboardViewModel leaderboardViewModel;
    private final GameViewModel gameViewModel;
    private final StatsPageViewModel statsPageViewModel;

    // Controllers
    private final GameController gameController;

    // Views
    private final AppViewManager viewManager;

    public App(ViewManagerModel viewManagerModel, AccountViewModel accountViewModel,
               LeaderboardViewModel leaderboardViewModel, GameViewModel gameViewModel,
               GameController gameController, StatsPageViewModel statsPageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.accountViewModel = accountViewModel;
        this.leaderboardViewModel = leaderboardViewModel;
        this.gameViewModel = gameViewModel;
        this.gameController = gameController;
        this.statsPageViewModel = statsPageViewModel;

        this.viewManager = new AppViewManager(this);
        viewManager.init();
    }

    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }

    public AccountViewModel getAccountViewModel() {
        return accountViewModel;
    }

    public LeaderboardViewModel getLeaderboardViewModel() {
        return leaderboardViewModel;
    }

    public AppViewManager getViewManager() {
        return viewManager;
    }

    public GameController getGameController() {
        return gameController;
    }

    public GameViewModel getGameViewModel() {
        return gameViewModel;
    }

    public StatsPageViewModel getStatsPageViewModel() { return statsPageViewModel;}

    public void show() {
        viewManager.navigate("main");
        viewManager.setVisible(true);
        viewManager.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewManager.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        GameViewModel viewModel = new GameViewModel();
        GamePresenter presenter = new GamePresenter(viewModel, viewManagerModel);
        GameInteractor interactor = new GameInteractor(new DataAccessMock(), presenter);
        GameController controller = new GameController(interactor);
        AccountViewModel accountViewModel = new AccountViewModel();
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
        StatsPageViewModel statsPageViewModel = new StatsPageViewModel();

        leaderboardViewModel.setState(getLeaderboardState());
        accountViewModel.setState(new AccountState(false, "", "", ""));

        App app = new App(
                viewManagerModel, accountViewModel, leaderboardViewModel, viewModel, controller, statsPageViewModel
        );

        app.show();
    }

    private static LeaderboardState getLeaderboardState() {
        return new LeaderboardState(List.of(new DummyUserStats("Zev", 123), new DummyUserStats("Chris", 233), new DummyUserStats("Terrence", 198)));
    }

}
