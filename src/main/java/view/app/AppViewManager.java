package view.app;

import interface_adapter.ViewManagerModel;
import view.pages.*;

public class AppViewManager extends ViewManager {

    private MainPage mainPage;
    private GamePage gamePage;
    private AccountPage accountPage;
    private StatsPage statsPage;
    private GameSummaryPage gameSummaryPage;

    private MultiplayerPage multiplayerPage;

    private mGamePage mgamePage;

    private ErrorPage errorPage;

    private WaitingPage waitingPage;

    private EndMultiplayerGamePage endMultiplayerGamePage;

    private final App app;

    public AppViewManager(App app) {
        super(app.getViewManagerModel());

        this.app = app;
    }

    public void init() {
        mainPage = new MainPage(app);
        gamePage = new GamePage(app, app.getGameController(), app.getGameViewModel());
        accountPage = new AccountPage(app);
        statsPage = new StatsPage(app);
        gameSummaryPage = new GameSummaryPage(app);
        multiplayerPage = new MultiplayerPage(app, app.getMultiplayerController());
        mgamePage = new mGamePage(app, app.getMgameController(), app.getMgameViewModel());
        errorPage = new ErrorPage(app);
        waitingPage = new WaitingPage(app);
        endMultiplayerGamePage = new EndMultiplayerGamePage(app);

        add("main", mainPage);
        add("game", gamePage);
        add("mgame", mgamePage);
        add("account", accountPage);
        add("stats", statsPage);
        add("summary", gameSummaryPage);
        add("multiplayer", multiplayerPage);
        add("error", errorPage);
        add("wait", waitingPage);
        add("endmgame", endMultiplayerGamePage);
    }
}
