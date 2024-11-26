package view.app;

import interface_adapter.ViewManagerModel;
import view.pages.*;

public class AppViewManager extends ViewManager {

    private MainPage mainPage;
    private GamePage gamePage;
    private AccountPage accountPage;
    private StatsPage statsPage;

    private final App app;

    public AppViewManager(App app) {
        super(app.getViewManagerModel());

        this.app = app;
    }

    public void init() {
        mainPage = new MainPage(app);
        gamePage = new GamePage(app);
        accountPage = new AccountPage(app);
        statsPage = new StatsPage(app);

        add("main", mainPage);
        add("game", gamePage);
        add("account", accountPage);
        add("stats", statsPage);
    }
}
