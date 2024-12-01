package view.components;

import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountViewModel;
import interface_adapter.accountconfirm.AccountConfirmController;
import interface_adapter.accountdelete.AccountDeleteController;
import interface_adapter.accountlogout.AccountLogoutController;
import interface_adapter.game.GameController;
import interface_adapter.game.GameSummaryController;
import interface_adapter.game.GameSummaryPageViewModel;
import interface_adapter.game.GameViewModel;
import interface_adapter.image.ImagePageController;
import interface_adapter.image.ImagePageViewModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.login.LogInViewModel;
import interface_adapter.login.LogInController;
import interface_adapter.mgame.MGameEndViewModel;
import interface_adapter.multiplayer.MultiplayerController;
import interface_adapter.multiplayer.MultiplayerViewModel;
import interface_adapter.signup.SignUpController;
import interface_adapter.signup.SignUpViewModel;
import interface_adapter.stats.StatsController;
import interface_adapter.stats.StatsPageViewModel;
import interface_adapter.stats.UpdateStatsController;
import view.pages.ViewManager;

import javax.swing.*;

/**
 * Manages the application views, including their models and controllers.
 */
public class AppViewManager extends ViewManager {

    // View models
    private AccountViewModel accountViewModel;
    private LeaderboardViewModel leaderboardViewModel;
    private GameViewModel gameViewModel;
    private StatsPageViewModel statsPageViewModel;
    private GameSummaryPageViewModel summaryPageViewModel;
    private GameViewModel mgameViewModel;
    private MultiplayerViewModel multiplayerViewModel;
    private MGameEndViewModel mGameEndViewModel;
    private ImagePageViewModel imagePageViewModel;
    private SignUpViewModel signUpViewModel;
    private LogInViewModel loginViewModel;

    // Controllers
    private GameController gameController;
    private GameController mgameController;
    private MultiplayerController multiplayerController;
    private StatsController statsController;
    private AccountConfirmController accountConfirmController;
    private AccountLogoutController accountLogoutController;
    private AccountDeleteController accountDeleteController;
    private GameSummaryController gameSummaryController;
    private UpdateStatsController updateStatsController;
    private ImagePageController imagePageController;
    private SignUpController signUpController;
    private LogInController loginController;

    /**
     * Constructs an AppViewManager with a default view manager model.
     */
    public AppViewManager() {
        super(new ViewManagerModel());
    }

    /**
     * Returns the current view manager model.
     *
     * @return the view manager model
     */
    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }

    /**
     * Returns this AppViewManager instance.
     *
     * @return this view manager
     */
    public AppViewManager getViewManager() {
        return this;
    }

    /**
     * Displays the main page, maximizing the window and centering it on the screen.
     */
    public void showPage() {
        navigate("main");
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }

    /**
     * Returns the account view model.
     *
     * @return the account view model
     */
    public AccountViewModel getAccountViewModel() {
        return accountViewModel;
    }

    /**
     * Sets the account view model.
     *
     * @param accountViewModel the account view model to set
     */
    public void setAccountViewModel(AccountViewModel accountViewModel) {
        this.accountViewModel = accountViewModel;
    }

    /**
     * Returns the leaderboard view model.
     *
     * @return the leaderboard view model
     */
    public LeaderboardViewModel getLeaderboardViewModel() {
        return leaderboardViewModel;
    }

    /**
     * Sets the leaderboard view model.
     *
     * @param leaderboardViewModel the leaderboard view model to set
     */
    public void setLeaderboardViewModel(LeaderboardViewModel leaderboardViewModel) {
        this.leaderboardViewModel = leaderboardViewModel;
    }

    /**
     * Returns the game view model.
     *
     * @return the game view model
     */
    public GameViewModel getGameViewModel() {
        return gameViewModel;
    }

    /**
     * Sets the game view model.
     *
     * @param gameViewModel the game view model to set
     */
    public void setGameViewModel(GameViewModel gameViewModel) {
        this.gameViewModel = gameViewModel;
    }

    /**
     * Returns the stats page view model.
     *
     * @return the stats page view model
     */
    public StatsPageViewModel getStatsPageViewModel() {
        return statsPageViewModel;
    }

    /**
     * Sets the stats page view model.
     *
     * @param statsPageViewModel the stats page view model to set
     */
    public void setStatsPageViewModel(StatsPageViewModel statsPageViewModel) {
        this.statsPageViewModel = statsPageViewModel;
    }

    /**
     * Returns the game summary page view model.
     *
     * @return the game summary page view model
     */
    public GameSummaryPageViewModel getGameSummaryPageViewModel() {
        return summaryPageViewModel;
    }

    /**
     * Sets the game summary page view model.
     *
     * @param summaryPageViewModel the game summary page view model to set
     */
    public void setSummaryPageViewModel(GameSummaryPageViewModel summaryPageViewModel) {
        this.summaryPageViewModel = summaryPageViewModel;
    }

    /**
     * Returns the multiplayer game view model.
     *
     * @return the multiplayer game view model
     */
    public GameViewModel getMgameViewModel() {
        return mgameViewModel;
    }

    /**
     * Sets the multiplayer game view model.
     *
     * @param mgameViewModel the multiplayer game view model to set
     */
    public void setMgameViewModel(GameViewModel mgameViewModel) {
        this.mgameViewModel = mgameViewModel;
    }

    /**
     * Returns the multiplayer view model.
     *
     * @return the multiplayer view model
     */
    public MultiplayerViewModel getMultiplayerViewModel() {
        return multiplayerViewModel;
    }

    /**
     * Sets the multiplayer view model.
     *
     * @param multiplayerViewModel the multiplayer view model to set
     */
    public void setMultiplayerViewModel(MultiplayerViewModel multiplayerViewModel) {
        this.multiplayerViewModel = multiplayerViewModel;
    }

    /**
     * Returns the multiplayer game end view model.
     *
     * @return the multiplayer game end view model
     */
    public MGameEndViewModel getmGameEndViewModel() {
        return mGameEndViewModel;
    }

    /**
     * Sets the multiplayer game end view model.
     *
     * @param mGameEndViewModel the multiplayer game end view model to set
     */
    public void setmGameEndViewModel(MGameEndViewModel mGameEndViewModel) {
        this.mGameEndViewModel = mGameEndViewModel;
    }

    /**
     * Returns the image page view model.
     *
     * @return the image page view model
     */
    public ImagePageViewModel getImagePageViewModel() {
        return imagePageViewModel;
    }

    /**
     * Sets the image page view model.
     *
     * @param imagePageViewModel the image page view model to set
     */
    public void setImagePageViewModel(ImagePageViewModel imagePageViewModel) {
        this.imagePageViewModel = imagePageViewModel;
    }


    /**
     * Returns the sign-up view model.
     *
     * @return the sign-up view model.
     */
    public SignUpViewModel getSignUpViewModel() {
        return signUpViewModel;
    }

    /**
     * Sets the sign-up view model.
     *
     * @param signUpViewModel the sign-up view model to set.
     */
    public void setSignUpViewModel(SignUpViewModel signUpViewModel) {
        this.signUpViewModel = signUpViewModel;
    }

    /**
     * Returns the log-in view model.
     *
     * @return the log-in view model.
     */
    public LogInViewModel getLoginViewModel() {
        return loginViewModel;
    }

    /**
     * Sets the log-in view model.
     *
     * @param loginViewModel the sign-up view model to set.
     */
    public void setLoginViewModel(LogInViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    /**
     * Returns the game controller.
     *
     * @return the game controller
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Sets the game controller.
     *
     * @param gameController the game controller to set
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Returns the multiplayer game controller.
     *
     * @return the multiplayer game controller
     */
    public GameController getMgameController() {
        return mgameController;
    }

    /**
     * Sets the multiplayer game controller.
     *
     * @param mgameController the multiplayer game controller to set
     */
    public void setMgameController(GameController mgameController) {
        this.mgameController = mgameController;
    }

    /**
     * Returns the multiplayer controller.
     *
     * @return the multiplayer controller
     */
    public MultiplayerController getMultiplayerController() {
        return multiplayerController;
    }

    /**
     * Sets the multiplayer controller.
     *
     * @param multiplayerController the multiplayer controller to set
     */
    public void setMultiplayerController(MultiplayerController multiplayerController) {
        this.multiplayerController = multiplayerController;
    }

    /**
     * Returns the stats controller.
     *
     * @return the stats controller
     */
    public StatsController getStatsController() {
        return statsController;
    }

    /**
     * Sets the stats controller.
     *
     * @param statsController the stats controller to set
     */
    public void setStatsController(StatsController statsController) {
        this.statsController = statsController;
    }

    /**
     * Returns the account confirm controller.
     *
     * @return the account confirm controller
     */
    public AccountConfirmController getAccountConfirmController() {
        return accountConfirmController;
    }

    /**
     * Sets the account confirm controller.
     *
     * @param accountConfirmController the account confirm controller to set
     */
    public void setAccountConfirmController(AccountConfirmController accountConfirmController) {
        this.accountConfirmController = accountConfirmController;
    }

    /**
     * Returns the account logout controller.
     *
     * @return the account logout controller
     */
    public AccountLogoutController getAccountLogoutController() {
        return accountLogoutController;
    }

    /**
     * Sets the account logout controller.
     *
     * @param accountLogoutController the account logout controller to set
     */
    public void setAccountLogoutController(AccountLogoutController accountLogoutController) {
        this.accountLogoutController = accountLogoutController;
    }

    /**
     * Returns the account delete controller.
     *
     * @return the account delete controller
     */
    public AccountDeleteController getAccountDeleteController() {
        return accountDeleteController;
    }

    /**
     * Sets the account delete controller.
     *
     * @param accountDeleteController the account delete controller to set
     */
    public void setAccountDeleteController(AccountDeleteController accountDeleteController) {
        this.accountDeleteController = accountDeleteController;
    }

    /**
     * Returns the game summary controller.
     *
     * @return the game summary controller
     */
    public GameSummaryController getGameSummaryController() {
        return gameSummaryController;
    }

    /**
     * Sets the game summary controller.
     *
     * @param gameSummaryController the game summary controller to set
     */
    public void setGameSummaryController(GameSummaryController gameSummaryController) {
        this.gameSummaryController = gameSummaryController;
    }

    /**
     * Returns the update stats controller.
     *
     * @return the update stats controller
     */
    public UpdateStatsController getUpdateStatsController() {
        return updateStatsController;
    }

    /**
     * Sets the update stats controller.
     *
     * @param updateStatsController the update stats controller to set
     */
    public void setUpdateStatsController(UpdateStatsController updateStatsController) {
        this.updateStatsController = updateStatsController;
    }

    /**
     * Returns the image page controller.
     *
     * @return the image page controller
     */
    public ImagePageController getImagePageController() {
        return imagePageController;
    }

    /**
     * Sets the image page controller.
     *
     * @param imagePageController the image page controller to set
     */
    public void setImagePageController(ImagePageController imagePageController) {
        this.imagePageController = imagePageController;
    }

    /**
     * Returns the sign-up controller.
     *
     * @return the sign-up controller.
     */
    public SignUpController getSignUpController() {
        return signUpController;
    }

    /**
     * Sets the sign-up controller.
     *
     * @param signUpController the image page controller to set
     */
    public void setSignUpController(SignUpController signUpController) {
        this.signUpController = signUpController;
    }

    /**
     * Returns the log-in controller.
     *
     * @return the log-in controller.
     */
    public LogInController getLoginController() {
        return loginController;
    }

    /**
     * Sets the log-in controller.
     *
     * @param loginController the image page controller to set
     */
    public void setLoginController(LogInController loginController) {
        this.loginController = loginController;
    }
}
