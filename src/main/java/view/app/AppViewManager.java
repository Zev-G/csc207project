package view.app;

import view.pages.*;

public class AppViewManager extends ViewManager {

    private MainPage mainPage;
    private GamePage gamePage;
    private AccountPage accountPage;

    private MultiplayerPage multiplayerPage;

    private mGamePage mgamePage;

    private final App app;

    public AppViewManager(App app) {
        super(app.getViewManagerModel());

        this.app = app;
    }

    public void init() {
        mainPage = new MainPage(app);
        gamePage = new GamePage(app, app.getGameController(), app.getGameViewModel());
        accountPage = new AccountPage(app);
        multiplayerPage = new MultiplayerPage(app, app.getMultiplayerController());
        mgamePage = new mGamePage(app, app.getMgameController(), app.getMgameViewModel());

        add("main", mainPage);
        add("game", gamePage);
        add("mgame", mgamePage);
        add("account", accountPage);
        add("multiplayer", multiplayerPage);
    }


}
