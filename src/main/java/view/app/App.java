package view.app;

import app.FirebaseInitializer;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import data_access.*;
import entity.DummyUserStats;
import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import interface_adapter.game.*;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.stats.StatsController;
import interface_adapter.stats.StatsPageViewModel;
import interface_adapter.stats.StatsPresenter;
import use_case.game.*;
import use_case.stats.StatsInteractor;
import view.pages.ViewManager;

import data_access.DataAccessMock;
import entity.DummyUserStats;
import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import interface_adapter.accountconfirm.AccountConfirmController;
import interface_adapter.accountconfirm.AccountConfirmPresenter;
import interface_adapter.accountdelete.AccountDeleteController;
import interface_adapter.accountdelete.AccountDeletePresenter;
import interface_adapter.accountlogout.AccountLogoutController;
import interface_adapter.accountlogout.AccountLogoutPresenter;
import interface_adapter.game.GameController;
import interface_adapter.game.GamePresenter;
import interface_adapter.game.GameViewModel;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import use_case.accountconfirm.AccountConfirmInteractor;
import use_case.accountdelete.AccountDeleteInteractor;
import use_case.accountlogout.AccountLogoutInteractor;
import use_case.game.GameInteractor;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class App {

    // Models
    private final AccountViewModel accountViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LeaderboardViewModel leaderboardViewModel;
    private final GameViewModel gameViewModel;
    private final StatsPageViewModel statsPageViewModel;
    private final GameSummaryPageViewModel summaryPageViewModel;

    // Controllers
    private final GameController gameController;
    private final StatsController statsController;
    private final AccountConfirmController accountConfirmController;
    private final AccountLogoutController accountLogoutController;
    private final AccountDeleteController accountDeleteController;
    private final GameSummaryController gameSummaryController;

    // Views
    private final AppViewManager viewManager;

    public App(ViewManagerModel viewManagerModel, AccountViewModel accountViewModel,
               LeaderboardViewModel leaderboardViewModel, GameViewModel gameViewModel,
               GameController gameController, StatsPageViewModel statsPageViewModel, StatsController statsController, AccountConfirmController accountConfirmController,
               AccountLogoutController accountLogoutController,
               AccountDeleteController accountDeleteController, GameSummaryController gameSummaryController, GameSummaryPageViewModel summaryPageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.accountViewModel = accountViewModel;
        this.leaderboardViewModel = leaderboardViewModel;
        this.gameViewModel = gameViewModel;
        this.gameController = gameController;
        this.statsPageViewModel = statsPageViewModel;
        this.statsController = statsController;
        this.accountConfirmController = accountConfirmController;
        this.accountLogoutController = accountLogoutController;
        this.accountDeleteController = accountDeleteController;
        this.gameSummaryController = gameSummaryController;
        this.summaryPageViewModel = summaryPageViewModel;

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

    public StatsPageViewModel getStatsPageViewModel() { return statsPageViewModel; }

    public StatsController getStatsController() {return statsController; }

    public GameSummaryController getGameSummaryController() {
        return gameSummaryController;
    }

    public AccountConfirmController getAccountConfirmController() {
        return accountConfirmController;
    }

    public AccountLogoutController getAccountLogoutController() {
        return accountLogoutController;
    }

    public AccountDeleteController getAccountDeleteController() {
        return accountDeleteController;
    }

    public GameSummaryPageViewModel getGameSummaryPageViewModel() {
        return summaryPageViewModel;
    }

    public void show() {
        viewManager.navigate("main");
        viewManager.setVisible(true);
        viewManager.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewManager.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // Initialize Firebase
        try {
            FirebaseInitializer.initializeFirebase();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to initialize Firebase. Exiting...");
            return; // Exit if Firebase initialization fails
        }
        DataAccessMock mock = new DataAccessMock();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        GameViewModel viewModel = new GameViewModel();
        GamePresenter presenter = new GamePresenter(viewModel, viewManagerModel);
        GameInteractor interactor = new GameInteractor(new DataAccessMock(), presenter);
        GameController controller = new GameController(interactor);
        AccountViewModel accountViewModel = new AccountViewModel();
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        StatsDataAccess statsDataAccess = new FirebaseStatsDataAccess(databaseReference);
        StatsPageViewModel statsPageViewModel = new StatsPageViewModel();
        StatsPresenter statsPresenter = new StatsPresenter(statsPageViewModel);
        StatsInteractor statsInteractor = new StatsInteractor(statsDataAccess, statsPresenter);
        StatsController statsController = new StatsController(statsInteractor);
        GameSummaryDataAccess gameSummaryDataAccess = new GameSummaryDataAccess();
        GameSummaryPageViewModel summaryPageViewModel = new GameSummaryPageViewModel();
        GameSummaryOutputBoundary gameSummaryPresenter = new GameSummaryPresenter(summaryPageViewModel);
        GameSummaryInputBoundary gameSummaryInteractor = new GameSummaryInteractor(gameSummaryPresenter);
        GameSummaryController gameSummaryController = new GameSummaryController(gameSummaryInteractor);

        AccountConfirmPresenter accountConfirmPresenter = new AccountConfirmPresenter(viewManagerModel);
        AccountConfirmInteractor accountConfirmInteractor = new AccountConfirmInteractor(mock, accountConfirmPresenter);
        AccountConfirmController accountConfirmController = new AccountConfirmController(accountConfirmInteractor);

        AccountLogoutPresenter accountLogoutPresenter = new AccountLogoutPresenter(viewManagerModel, accountViewModel);
        AccountLogoutInteractor accountLogoutInteractor = new AccountLogoutInteractor(accountLogoutPresenter);
        AccountLogoutController accountLogoutController = new AccountLogoutController(accountLogoutInteractor);

        AccountDeletePresenter accountDeletePresenter = new AccountDeletePresenter(viewManagerModel, accountViewModel);
        AccountDeleteInteractor accountDeleteInteractor = new AccountDeleteInteractor(accountDeletePresenter, mock);
        AccountDeleteController accountDeleteController = new AccountDeleteController(accountDeleteInteractor);

        leaderboardViewModel.setState(getLeaderboardState());
        accountViewModel.setState(new AccountState(false, "", "", "",0));

        App app = new App(
                viewManagerModel, accountViewModel, leaderboardViewModel, viewModel, controller, statsPageViewModel, statsController,
                accountConfirmController, accountLogoutController,
                accountDeleteController, gameSummaryController, summaryPageViewModel
        );

        app.show();
    }

    private static LeaderboardState getLeaderboardState() {
        return new LeaderboardState(List.of(new DummyUserStats("Zev", 123), new DummyUserStats("Chris", 233), new DummyUserStats("Terrence", 198)));
    }
}
