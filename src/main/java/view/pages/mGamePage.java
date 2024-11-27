package view.pages;

import interface_adapter.game.GameController;
import interface_adapter.game.GameViewModel;
import view.app.App;
import view.components.game.GameTimer;
import view.components.game.InteractiveMap;
import view.components.game.PointsDisplay;
import view.components.standard.RoundedButton;
import view.components.game.SegmentedProgressBar;
import view.utils.ImageScaler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
    public mGamePage(App app, GameController gameController, GameViewModel gameViewModel) {
        super(app, gameController, gameViewModel);
    }

    @Override
    public void init() {
        gameTimer.resetTimer();
        gameTimer.start();
    }
}
