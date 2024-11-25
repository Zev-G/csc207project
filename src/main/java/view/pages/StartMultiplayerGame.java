package view.pages;

import data_access.DataAccessMock;
import interface_adapter.ViewManagerModel;
import interface_adapter.game.GameController;
import interface_adapter.game.GameViewModel;
import interface_adapter.mgame.MGamePresenter;
import interface_adapter.multiplayer.MultiplayerController;
import use_case.mgame.MGameInteractor;
import use_case.multiplayer.MultiplayerInteractor;
import use_case.multiplayer.MultiplayerOutputBoundary;
import view.components.standard.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMultiplayerGame extends Page{

    GameController gameController;

    MultiplayerController multiplayerController;
    protected StartMultiplayerGame(ViewManager viewManager, MultiplayerController multiplayerController) {
        super(viewManager);
        this.multiplayerController = multiplayerController;

        TextField username = new TextField();
        TextField opponentUsername = new TextField();
        RoundedButton button = new RoundedButton("Start");

        add(username);

        add(opponentUsername);

        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                multiplayerController.execute(username.getText(), opponentUsername.getText());
            }
        });

    }

    public static void main(String[] args) {

//        MultiplayerOutputBoundary presenter = new MultiplayerOutputBoundary() {
//            @Override
//            public void prepareTimeoutView() {
//                System.out.println("timeout");
//            }
//
//            @Override
//            public void prepareErrorView() {
//                System.out.println("error");
//            }
//        };
//
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        ViewManager viewManager = new ViewManager(viewManagerModel);
//
//        GameViewModel viewModel = new GameViewModel();
//        MGamePresenter gamePresenter = new MGamePresenter(viewModel, viewManagerModel);
//        MGameInteractor interactor = new MGameInteractor(new DataAccessMock(), gamePresenter);
//        GameController controller = new GameController(interactor);
//
//        MultiplayerInteractor multiplayerInteractor = new MultiplayerInteractor("localhost",5555, presenter, interactor);
//
//        MultiplayerController multiplayerController1 = new MultiplayerController(multiplayerInteractor);
//
//        viewManager.add("game", new GamePage(viewManager, viewModel, controller));
//        viewManager.add("start", new StartMultiplayerGame(viewManager, multiplayerController1));
//
//        viewManager.navigate("start");
//        viewManager.setVisible(true);
//        viewManager.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        viewManager.setLocationRelativeTo(null);
    }
}
