package app;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import data_access.*;
import entity.DummyUserStats;
import interface_adapter.ErrorHandlingViewModel;
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
import interface_adapter.image.ImagePageController;
import interface_adapter.image.ImagePagePresenter;
import interface_adapter.image.ImagePageViewModel;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.mgame.MGameEndViewModel;
import interface_adapter.mgame.MGamePresenter;
import interface_adapter.multiplayer.MultiplayerController;
import interface_adapter.multiplayer.MultiplayerPresenter;
import interface_adapter.multiplayer.MultiplayerViewModel;
import interface_adapter.signup.SignUpController;
import interface_adapter.signup.SignUpPresenter;
import interface_adapter.signup.SignUpState;
import interface_adapter.signup.SignUpViewModel;
import interface_adapter.login.LogInController;
import interface_adapter.login.LogInPresenter;
import interface_adapter.login.LogInState;
import interface_adapter.login.LogInViewModel;
import interface_adapter.stats.StatsController;
import interface_adapter.stats.StatsPageViewModel;
import interface_adapter.stats.StatsPresenter;
import interface_adapter.stats.UpdateStatsController;
import use_case.accountconfirm.AccountConfirmInteractor;
import use_case.accountdelete.AccountDeleteInteractor;
import use_case.accountlogout.AccountLogoutInteractor;
import use_case.dataAccessInterface.LocationDataAccess;
import use_case.dataAccessInterface.StatsDataAccess;
import use_case.dataAccessInterface.UserDataAccess;
import use_case.game.GameInteractor;
import use_case.game.GameSummaryInputBoundary;
import use_case.game.GameSummaryInteractor;
import use_case.game.GameSummaryOutputBoundary;
import use_case.image.ImagePageInteractor;
import data_access.ImageUploadDataAccess;
import use_case.mgame.MGameInteractor;
import use_case.multiplayer.MultiplayerInteractor;
import use_case.signup.SignUpInputBoundary;
import use_case.signup.SignUpInteractor;
import use_case.signup.SignUpOutputBoundary;
import use_case.login.LogInInputBoundary;
import use_case.login.LogInInteractor;
import use_case.login.LogInOutputBoundary;
import use_case.stats.StatsInteractor;
import use_case.stats.StatsRepository;
import use_case.stats.UpdateStatsInteractor;
import view.components.AppViewManager;
import view.pages.*;

import java.io.IOException;
import java.util.List;

public class AppBuilder {
    private final AppViewManager app = new AppViewManager();
    private final AccountViewModel accountViewModel = new AccountViewModel();
    private final LocationDataAccess locationDataAccess = new PhotoLocationDataAccess();

    public AppBuilder setupFirebase() {
        try {
            FirebaseInitializer.initializeFirebase();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to initialize Firebase. Exiting...");
            System.exit(-1);
        }
        return this;
    }

    public AppBuilder setupGame() {
        ViewManagerModel viewManagerModel = app.getViewManagerModel();
        GameViewModel viewModel = new GameViewModel();
        GamePresenter presenter = new GamePresenter(viewModel, viewManagerModel);
        GameInteractor interactor = new GameInteractor(locationDataAccess, presenter);
        GameController controller = new GameController(interactor);
        app.setGameController(controller);
        app.setGameViewModel(viewModel);
        return this;
    }

    public AppBuilder setupMGame() {
        // Game setup
        MGameEndViewModel mGameEndViewModel = new MGameEndViewModel();
        GameViewModel mgameViewModel = new GameViewModel();
        MGamePresenter mgamePresenter = new MGamePresenter(mgameViewModel, app.getViewManagerModel(), mGameEndViewModel);
        MGameInteractor mGameInteractor = new MGameInteractor(locationDataAccess, mgamePresenter);
        GameController mgameController = new GameController(mGameInteractor);

        // Connection setup
        MultiplayerViewModel multiplayerViewModel = new MultiplayerViewModel();
        MultiplayerPresenter multiplayerPresenter = new MultiplayerPresenter(multiplayerViewModel);
        MultiplayerInteractor multiplayerInteractor = new MultiplayerInteractor("app.kristopherz.net", 5555,
                multiplayerPresenter, mGameInteractor);
        MultiplayerController multiplayerController = new MultiplayerController(multiplayerInteractor);

        app.setMgameController(mgameController);
        app.setMgameViewModel(mgameViewModel);
        app.setmGameEndViewModel(mGameEndViewModel);
        app.setMultiplayerController(multiplayerController);
        app.setMultiplayerViewModel(multiplayerViewModel);
        return this;
    }

    public AppBuilder setupAccount() {
        UserDataAccess data = new FirebaseLogInDataAccess(FirebaseDatabase.getInstance().getReference());

        final ErrorHandlingViewModel errorHandlingViewModel = new ErrorHandlingViewModel("error-handling");
        app.setErrorHandlingViewModel(errorHandlingViewModel);

        AccountConfirmPresenter accountConfirmPresenter = new AccountConfirmPresenter(app.getViewManagerModel());
        AccountConfirmInteractor accountConfirmInteractor = new AccountConfirmInteractor(data, accountConfirmPresenter);
        AccountConfirmController accountConfirmController = new AccountConfirmController(accountConfirmInteractor);

        AccountLogoutPresenter accountLogoutPresenter = new AccountLogoutPresenter(app.getViewManagerModel(), accountViewModel);
        AccountLogoutInteractor accountLogoutInteractor = new AccountLogoutInteractor(accountLogoutPresenter);
        AccountLogoutController accountLogoutController = new AccountLogoutController(accountLogoutInteractor);

        AccountDeletePresenter accountDeletePresenter = new AccountDeletePresenter(app.getViewManagerModel(), errorHandlingViewModel, accountViewModel);
        AccountDeleteInteractor accountDeleteInteractor = new AccountDeleteInteractor(accountDeletePresenter, data);
        AccountDeleteController accountDeleteController = new AccountDeleteController(accountDeleteInteractor);

        accountViewModel.setState(new AccountState(false, "", "", "", ""));

        app.setAccountViewModel(accountViewModel);
        app.setAccountConfirmController(accountConfirmController);
        app.setAccountLogoutController(accountLogoutController);
        app.setAccountDeleteController(accountDeleteController);
        return this;
    }

    public AppBuilder setupLeaderboard() {
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
        leaderboardViewModel.setState(getLeaderboardState());
        app.setLeaderboardViewModel(leaderboardViewModel);
        return this;
    }

    private static LeaderboardState getLeaderboardState() {
        return new LeaderboardState(List.of(new DummyUserStats("Zev", 123), new DummyUserStats("Chris", 233), new DummyUserStats("Terrence", 198)));
    }

    public AppBuilder setupStats() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        StatsRepository statsRepository = new FireBaseStatsUpdate(databaseReference);
        UpdateStatsInteractor updateStatsInteractor = new UpdateStatsInteractor(statsRepository);
        UpdateStatsController updateStatsController = new UpdateStatsController(updateStatsInteractor);

        StatsDataAccess statsDataAccess = new FirebaseStatsDataAccess(databaseReference);
        StatsPageViewModel statsPageViewModel = new StatsPageViewModel();
        StatsPresenter statsPresenter = new StatsPresenter(statsPageViewModel);
        StatsInteractor statsInteractor = new StatsInteractor(statsDataAccess, statsPresenter);
        StatsController statsController = new StatsController(statsInteractor);
        GameSummaryDataAccess gameSummaryDataAccess = new GameSummaryDataAccess();
        GameSummaryPageViewModel summaryPageViewModel = new GameSummaryPageViewModel();
        GameSummaryOutputBoundary gameSummaryPresenter = new GameSummaryPresenter(summaryPageViewModel);
        GameSummaryInputBoundary gameSummaryInteractor = new GameSummaryInteractor(gameSummaryPresenter);
        GameSummaryController gameSummaryController = new GameSummaryController(updateStatsInteractor, gameSummaryInteractor);

        app.setGameSummaryController(gameSummaryController);
        app.setSummaryPageViewModel(summaryPageViewModel);
        app.setStatsController(statsController);
        app.setStatsPageViewModel(statsPageViewModel);
        app.setUpdateStatsController(updateStatsController);
        return this;
    }

    public AppBuilder setupImage() {
        ImagePageViewModel imagePageViewModel = new ImagePageViewModel();
        ImagePagePresenter imagePagePresenter = new ImagePagePresenter(imagePageViewModel);
        ImageUploadDataAccess imageUploadDataAccess = new ImageUploadDataAccess("50ebc9d32abce50f92c2794ae7b36aa3e743b272");
        ImagePageInteractor imagePageInteractor = new ImagePageInteractor(imagePagePresenter, imageUploadDataAccess);
        ImagePageController imagePageController = new ImagePageController(imagePageInteractor);
        app.setImagePageViewModel(imagePageViewModel);
        app.setImagePageController(imagePageController);
        return this;
    }

    public AppBuilder setupSignUp() {

        SignUpViewModel signUpViewModel = new SignUpViewModel();
        SignUpState initialState = new SignUpState(false, null);
        signUpViewModel.setState(initialState);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseSignUpDataAccess firebaseSignUpDataAccess = new FirebaseSignUpDataAccess(databaseReference);

        SignUpOutputBoundary signUpOutputBoundary = new SignUpPresenter(signUpViewModel);
        SignUpInputBoundary signUpInputBoundary = new SignUpInteractor(firebaseSignUpDataAccess, signUpOutputBoundary);

        SignUpController signUpController = new SignUpController(signUpInputBoundary);

        app.setSignUpController(signUpController);
        app.setSignUpViewModel(signUpViewModel);

        return this;
    }

    public AppBuilder setupLogIn() {


        LogInViewModel logInViewModel = new LogInViewModel();
        LogInState initialState = new LogInState(false, false, "");
        logInViewModel.setState(initialState);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseLogInDataAccess firebaseLogInDataAccess = new FirebaseLogInDataAccess(databaseReference);

        LogInOutputBoundary logInPresenter = new LogInPresenter(logInViewModel, app.getViewManager());
        LogInInputBoundary logInInteractor = new LogInInteractor(firebaseLogInDataAccess, logInPresenter, accountViewModel);

        LogInController logInController = new LogInController(logInInteractor);

        app.setLoginController(logInController);
        app.setLoginViewModel(logInViewModel);

        return this;
    }


    public AppBuilder setupPages() {
        app.add("init", new InitPage(app));
        app.add("main", new MainPage(app));
        app.add("game", new GamePage(app, app.getGameController(), app.getGameViewModel()));
        app.add("account", new AccountPage(app));
        app.add("signup", new SignUpPage(app, app.getSignUpController(), app.getSignUpViewModel()));
        app.add("login", new LogInPage(app, app.getLoginController(), app.getLoginViewModel()));
        app.add("stats", new StatsPage(app));
        app.add("summary", new GameSummaryPage(app));
        app.add("multiplayer", new MultiplayerPage(app, app.getMultiplayerController()));
        app.add("mgame", new mGamePage(app, app.getMgameController(), app.getMgameViewModel()));
        app.add("error", new ErrorPage(app));
        app.add("wait", new WaitingPage(app));
        app.add("endmgame", new EndMultiplayerGamePage(app));
        app.add("image", new ImagePage(app, app.getImagePageController(), app.getImagePageViewModel()));
        return this;
    }

    public AppViewManager build() {
        return app;
    }
}
