package view.app;

import data_access.FirebaseInitializer;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import data_access.*;
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
import interface_adapter.game.*;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.mgame.MGameEndViewModel;
import interface_adapter.mgame.MGamePresenter;
import interface_adapter.multiplayer.MultiplayerController;
import interface_adapter.multiplayer.MultiplayerPresenter;
import interface_adapter.multiplayer.MultiplayerViewModel;
import interface_adapter.signup.*;
import interface_adapter.stats.StatsController;
import interface_adapter.stats.StatsPageViewModel;
import interface_adapter.stats.StatsPresenter;
import interface_adapter.signup.SignUpViewModel;
import use_case.accountconfirm.AccountConfirmInteractor;
import use_case.accountdelete.AccountDeleteInteractor;
import use_case.accountlogout.AccountLogoutInteractor;
import use_case.signup.SignUpInteractor;
import use_case.signup.SignUpOutputData;
import use_case.signup.SignUpInputData;
import use_case.signup.SignUpInputBoundary;
import use_case.signup.SignUpOutputBoundary;
import use_case.game.*;
import use_case.mgame.MGameInteractor;
import use_case.multiplayer.MultiplayerInteractor;
import use_case.stats.StatsInteractor;
import view.pages.SignUpPage;


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
    private final SignUpViewModel signUpViewModel;

    private final GameViewModel mgameViewModel;

    private final MultiplayerViewModel multiplayerViewModel;

    private final MGameEndViewModel mGameEndViewModel;

    // Controllers
    private final GameController gameController;
    private final GameController mgameController;
    private final MultiplayerController multiplayerController;
    private final StatsController statsController;
    private final AccountConfirmController accountConfirmController;
    private final AccountLogoutController accountLogoutController;
    private final AccountDeleteController accountDeleteController;
    private final GameSummaryController gameSummaryController;
    private final SignUpController signUpController;

    // Views
    private final AppViewManager viewManager;

    public App(
            // Views:
            ViewManagerModel viewManagerModel,
            // Models:
            AccountViewModel accountViewModel,
            LeaderboardViewModel leaderboardViewModel,
            GameViewModel gameViewModel,
            StatsPageViewModel statsPageViewModel,
            GameSummaryPageViewModel summaryPageViewModel,
            GameViewModel mgameViewModel,
            MultiplayerViewModel multiplayerViewModel,
            MGameEndViewModel mGameEndViewModel,
            SignUpViewModel signUpViewModel,
            // Controllers:
            GameController gameController,
            GameController mgameController,
            MultiplayerController multiplayerController,
            AccountConfirmController accountConfirmController,
            AccountLogoutController accountLogoutController,
            AccountDeleteController accountDeleteController,
            StatsController statsController,
            GameSummaryController gameSummaryController,
            SignUpController signUpController
    ) {
        // Model
        this.viewManagerModel = viewManagerModel;
        this.accountViewModel = accountViewModel;
        this.leaderboardViewModel = leaderboardViewModel;
        this.gameViewModel = gameViewModel;
        this.signUpViewModel = signUpViewModel;
        this.mgameViewModel = mgameViewModel;
        this.multiplayerViewModel = multiplayerViewModel;
        this.mGameEndViewModel = mGameEndViewModel;
        // Controllers
        this.gameController = gameController;
        this.mgameController = mgameController;
        this.multiplayerController = multiplayerController;
        this.statsPageViewModel = statsPageViewModel;
        this.statsController = statsController;
        this.accountConfirmController = accountConfirmController;
        this.accountLogoutController = accountLogoutController;
        this.accountDeleteController = accountDeleteController;
        this.gameSummaryController = gameSummaryController;
        this.summaryPageViewModel = summaryPageViewModel;
        this.signUpController = signUpController;

        this.viewManager = new AppViewManager(this);
        viewManager.init();
    }

    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }

    public AccountViewModel getAccountViewModel() {
        return accountViewModel;
    }

    public SignUpViewModel getSignUpViewModel() {
        return signUpViewModel;
    }

    public LeaderboardViewModel getLeaderboardViewModel() {
        return leaderboardViewModel;
    }

    public GameViewModel getGameViewModel() {
        return gameViewModel;
    }

    public GameViewModel getMgameViewModel() {
        return mgameViewModel;
    }

    public MultiplayerViewModel getMultiplayerViewModel() {
        return multiplayerViewModel;
    }

    public GameSummaryPageViewModel getGameSummaryPageViewModel() {
        return summaryPageViewModel;
    }

    public StatsPageViewModel getStatsPageViewModel() {
        return statsPageViewModel;
    }

    public MGameEndViewModel getmGameEndViewModel() {
        return mGameEndViewModel;
    }

    public GameController getGameController() {
        return gameController;
    }

    public MultiplayerController getMultiplayerController() {
        return multiplayerController;
    }

    public GameController getMgameController() {
        return mgameController;
    }

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

    public StatsController getStatsController() {
        return statsController;
    }

    public SignUpController getSignUpController() {
        return signUpController;
    }

    public AppViewManager getViewManager() {
        return viewManager;
    }

    public void show() {
        viewManager.navigate("main");
        viewManager.navigate("signUp");
        viewManager.setVisible(true);
        viewManager.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewManager.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // Initialize Firebases
        try {
            FirebaseInitializer.initializeFirebase();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to initialize Firebase. Exiting...");
            return; // Exit if Firebase initialization fails
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        SignUpDataAccess firebaseSignUpDataAccess = new FirebaseSignUpDataAccess(databaseReference);
        DataAccessMock mock = new DataAccessMock();

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        GameViewModel viewModel = new GameViewModel();
        GamePresenter presenter = new GamePresenter(viewModel, viewManagerModel);
        GameInteractor interactor = new GameInteractor(new DataAccessMock(), presenter);
        GameController controller = new GameController(interactor);

        MGameEndViewModel mGameEndViewModel = new MGameEndViewModel();
        GameViewModel mgameViewModel = new GameViewModel();
        MGamePresenter mgamePresenter = new MGamePresenter(mgameViewModel, viewManagerModel, mGameEndViewModel);
        MGameInteractor mGameInteractorinteractor = new MGameInteractor(new DataAccessMock(), mgamePresenter);
        GameController mgameController = new GameController(mGameInteractorinteractor);

        MultiplayerViewModel multiplayerViewModel = new MultiplayerViewModel();
        MultiplayerPresenter multiplayerPresenter = new MultiplayerPresenter(multiplayerViewModel);
        MultiplayerInteractor multiplayerInteractor = new MultiplayerInteractor("app.kristopherz.net", 5555,
                multiplayerPresenter, mGameInteractorinteractor);
        MultiplayerController multiplayerController = new MultiplayerController(multiplayerInteractor);



        AccountViewModel accountViewModel = new AccountViewModel();
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
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

        SignUpViewModel signUpViewModel = new SignUpViewModel();
        signUpViewModel.setState(new SignUpState(false, null));
        SignUpInteractor signUpInteractor = new SignUpInteractor(firebaseSignUpDataAccess, new SignUpPresenter(signUpViewModel));
        SignUpController signUpController = new SignUpController(signUpInteractor);

        App app = new App(
                viewManagerModel,
                accountViewModel,
                leaderboardViewModel,
                viewModel,
                statsPageViewModel,
                summaryPageViewModel,
                mgameViewModel,
                multiplayerViewModel,
                mGameEndViewModel,
                signUpViewModel,
                controller,
                mgameController,
                multiplayerController,
                accountConfirmController,
                accountLogoutController,
                accountDeleteController,
                statsController,
                gameSummaryController,
                signUpController
        );

        app.show();

    }

    private static LeaderboardState getLeaderboardState() {
        return new LeaderboardState(List.of(new DummyUserStats("Zev", 123), new DummyUserStats("Chris", 233), new DummyUserStats("Terrence", 198)));
    }

}

