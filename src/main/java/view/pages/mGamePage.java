package view.pages;

import interface_adapter.game.GameController;
import interface_adapter.game.GameViewModel;
import view.components.AppViewManager;

/**
 * A game page.
 */
public class mGamePage extends GamePage {


    /**
     * To make a game page.
     *
     * @param app            the app that is running the game
     * @param gameController
     * @param gameViewModel
     */
    public mGamePage(AppViewManager app, GameController gameController, GameViewModel gameViewModel) {
        super(app, gameController, gameViewModel);
    }

    @Override
    public void init() {
        resetPage();
    }
}
