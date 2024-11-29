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
import interface_adapter.mgame.MGameEndViewModel;
import interface_adapter.multiplayer.MultiplayerController;
import interface_adapter.multiplayer.MultiplayerViewModel;
import interface_adapter.stats.StatsController;
import interface_adapter.stats.StatsPageViewModel;
import interface_adapter.stats.UpdateStatsController;
import view.pages.*;

import javax.swing.*;

public class AppViewManager extends ViewManager {

    // Models
    private AccountViewModel accountViewModel;
    private LeaderboardViewModel leaderboardViewModel;
    private GameViewModel gameViewModel;
    private StatsPageViewModel statsPageViewModel;
    private GameSummaryPageViewModel summaryPageViewModel;

    private GameViewModel mgameViewModel;

    private MultiplayerViewModel multiplayerViewModel;

    private MGameEndViewModel mGameEndViewModel;
    private ImagePageViewModel imagePageViewModel;

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


    public AppViewManager() {
        super(new ViewManagerModel());
    }

    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }

    public AppViewManager getViewManager() {
        return this;
    }


    public void showPage() {
        navigate("main");
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }


    public AccountViewModel getAccountViewModel() {
        return accountViewModel;
    }

    public void setAccountViewModel(AccountViewModel accountViewModel) {
        this.accountViewModel = accountViewModel;
    }


    public LeaderboardViewModel getLeaderboardViewModel() {
        return leaderboardViewModel;
    }

    public void setLeaderboardViewModel(LeaderboardViewModel leaderboardViewModel) {
        this.leaderboardViewModel = leaderboardViewModel;
    }

    public GameViewModel getGameViewModel() {
        return gameViewModel;
    }

    public void setGameViewModel(GameViewModel gameViewModel) {
        this.gameViewModel = gameViewModel;
    }

    public StatsPageViewModel getStatsPageViewModel() {
        return statsPageViewModel;
    }

    public void setStatsPageViewModel(StatsPageViewModel statsPageViewModel) {
        this.statsPageViewModel = statsPageViewModel;
    }

    public GameSummaryPageViewModel getGameSummaryPageViewModel() {
        return summaryPageViewModel;
    }

    public void setSummaryPageViewModel(GameSummaryPageViewModel summaryPageViewModel) {
        this.summaryPageViewModel = summaryPageViewModel;
    }

    public GameViewModel getMgameViewModel() {
        return mgameViewModel;
    }

    public void setMgameViewModel(GameViewModel mgameViewModel) {
        this.mgameViewModel = mgameViewModel;
    }

    public MultiplayerViewModel getMultiplayerViewModel() {
        return multiplayerViewModel;
    }

    public void setMultiplayerViewModel(MultiplayerViewModel multiplayerViewModel) {
        this.multiplayerViewModel = multiplayerViewModel;
    }

    public MGameEndViewModel getmGameEndViewModel() {
        return mGameEndViewModel;
    }

    public void setmGameEndViewModel(MGameEndViewModel mGameEndViewModel) {
        this.mGameEndViewModel = mGameEndViewModel;
    }

    public ImagePageViewModel getImagePageViewModel() {
        return imagePageViewModel;
    }

    public void setImagePageViewModel(ImagePageViewModel imagePageViewModel) {
        this.imagePageViewModel = imagePageViewModel;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public GameController getMgameController() {
        return mgameController;
    }

    public void setMgameController(GameController mgameController) {
        this.mgameController = mgameController;
    }

    public MultiplayerController getMultiplayerController() {
        return multiplayerController;
    }

    public void setMultiplayerController(MultiplayerController multiplayerController) {
        this.multiplayerController = multiplayerController;
    }

    public StatsController getStatsController() {
        return statsController;
    }

    public void setStatsController(StatsController statsController) {
        this.statsController = statsController;
    }

    public AccountConfirmController getAccountConfirmController() {
        return accountConfirmController;
    }

    public void setAccountConfirmController(AccountConfirmController accountConfirmController) {
        this.accountConfirmController = accountConfirmController;
    }

    public AccountLogoutController getAccountLogoutController() {
        return accountLogoutController;
    }

    public void setAccountLogoutController(AccountLogoutController accountLogoutController) {
        this.accountLogoutController = accountLogoutController;
    }

    public AccountDeleteController getAccountDeleteController() {
        return accountDeleteController;
    }

    public void setAccountDeleteController(AccountDeleteController accountDeleteController) {
        this.accountDeleteController = accountDeleteController;
    }

    public GameSummaryController getGameSummaryController() {
        return gameSummaryController;
    }

    public void setGameSummaryController(GameSummaryController gameSummaryController) {
        this.gameSummaryController = gameSummaryController;
    }

    public UpdateStatsController getUpdateStatsController() {
        return updateStatsController;
    }

    public void setUpdateStatsController(UpdateStatsController updateStatsController) {
        this.updateStatsController = updateStatsController;
    }

    public ImagePageController getImagePageController() {
        return imagePageController;
    }

    public void setImagePageController(ImagePageController imagePageController) {
        this.imagePageController = imagePageController;
    }
}
