package view.app;

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
import interface_adapter.mgame.MGamePresenter;
import interface_adapter.multiplayer.MultiplayerController;
import interface_adapter.multiplayer.MultiplayerPresenter;
import interface_adapter.multiplayer.MultiplayerViewModel;
import use_case.accountconfirm.AccountConfirmInteractor;
import use_case.accountdelete.AccountDeleteInteractor;
import use_case.accountlogout.AccountLogoutInteractor;
import use_case.game.GameInteractor;
import use_case.mgame.MGameInteractor;
import use_case.multiplayer.MultiplayerInteractor;

import javax.swing.*;
import java.util.List;

public class App {

    // Models
    private final AccountViewModel accountViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LeaderboardViewModel leaderboardViewModel;
    private final GameViewModel gameViewModel;

    private final GameViewModel mgameViewModel;

    private final MultiplayerViewModel multiplayerViewModel;

    // Controllers
    private final GameController gameController;

    private final GameController mgameController;
    private final MultiplayerController multiplayerController;
    private final AccountConfirmController accountConfirmController;
    private final AccountLogoutController accountLogoutController;
    private final AccountDeleteController accountDeleteController;

    // Views
    private final AppViewManager viewManager;

    public App(
            // Views:
            ViewManagerModel viewManagerModel,
            // Models:
            AccountViewModel accountViewModel,
            LeaderboardViewModel leaderboardViewModel,
            GameViewModel gameViewModel,
            GameViewModel mgameViewModel,
            MultiplayerViewModel multiplayerViewModel,
            // Controllers:
            GameController gameController,
            GameController mgameController,
            MultiplayerController multiplayerController,
            AccountConfirmController accountConfirmController,
            AccountLogoutController accountLogoutController
               // Views:
               ViewManagerModel viewManagerModel,
               // Models:
               AccountViewModel accountViewModel,
               LeaderboardViewModel leaderboardViewModel,
               GameViewModel gameViewModel,
               // Controllers:
               GameController gameController,
               AccountConfirmController accountConfirmController,
               AccountLogoutController accountLogoutController,
               AccountDeleteController accountDeleteController
    ) {
        // Model
        this.viewManagerModel = viewManagerModel;
        this.accountViewModel = accountViewModel;
        this.leaderboardViewModel = leaderboardViewModel;
        this.gameViewModel = gameViewModel;
        this.mgameViewModel = mgameViewModel;
        this.multiplayerViewModel = multiplayerViewModel;
        // Controllers
        this.gameController = gameController;
        this.mgameController = mgameController;
        this.multiplayerController = multiplayerController;
        this.accountConfirmController = accountConfirmController;
        this.accountLogoutController = accountLogoutController;
        this.accountDeleteController = accountDeleteController;

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

    public GameController getMgameController(){
        return mgameController;
    }

    public MultiplayerController getMultiplayerController() {
        return multiplayerController;
    }

    public GameViewModel getGameViewModel() {
        return gameViewModel;
    }

    public GameViewModel getMgameViewModel() {
        return mgameViewModel;
    }

    public MultiplayerViewModel getMultiplayerViewModel(){
        return multiplayerViewModel;
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

    public void show() {
        viewManager.navigate("main");
        viewManager.setVisible(true);
        viewManager.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewManager.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        DataAccessMock mock = new DataAccessMock();

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        GameViewModel viewModel = new GameViewModel();
        GamePresenter presenter = new GamePresenter(viewModel, viewManagerModel);
        GameInteractor interactor = new GameInteractor(mock, presenter);
        GameController controller = new GameController(interactor);


        GameViewModel mgameViewModel = new GameViewModel();
        MGamePresenter mgamePresenter = new MGamePresenter(mgameViewModel, viewManagerModel);
        MGameInteractor mGameInteractorinteractor = new MGameInteractor(new DataAccessMock(), mgamePresenter);
        GameController mgameController = new GameController(mGameInteractorinteractor);

        MultiplayerViewModel multiplayerViewModel = new MultiplayerViewModel();

        MultiplayerPresenter multiplayerPresenter = new MultiplayerPresenter(multiplayerViewModel);
        MultiplayerInteractor multiplayerInteractor = new MultiplayerInteractor("app.kristopherz.net", 5555,
                multiplayerPresenter, mGameInteractorinteractor);
        MultiplayerController multiplayerController = new MultiplayerController(multiplayerInteractor);


        AccountViewModel accountViewModel = new AccountViewModel();
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();

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
        accountViewModel.setState(new AccountState(false, "", "", "", 0));

        App app = new App(
                viewManagerModel, accountViewModel, leaderboardViewModel,
                viewModel, controller, accountConfirmController, accountLogoutController,
                accountDeleteController,
                viewModel, mgameViewModel, multiplayerViewModel, controller, mgameController, multiplayerController
                , accountConfirmController, accountLogoutController
        );

        app.show();
    }

    private static LeaderboardState getLeaderboardState() {
        return new LeaderboardState(List.of(new DummyUserStats("Zev", 123), new DummyUserStats("Chris", 233), new DummyUserStats("Terrence", 198)));
    }

}
