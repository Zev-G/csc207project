package view.pages;

import data_access.DataAccessMock;
import interface_adapter.ViewManagerModel;
import interface_adapter.game.GameController;
import interface_adapter.game.GamePresenter;
import interface_adapter.game.GameViewModel;
import interface_adapter.multiplayer.MultiplayerController;
import use_case.game.GameInteractor;
import use_case.multiplayer.MultiplayerInteractor;
import use_case.multiplayer.MultiplayerOutputBoundary;
import use_case.multiplayer.MultiplayerOutputData;
import view.components.standard.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class StartMultiplayerGame extends Page{

    GameController gameController;

    MultiplayerController multiplayerController;
    protected StartMultiplayerGame(PageManager pageManager, MultiplayerController multiplayerController) {
        super(pageManager);
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

        MultiplayerOutputBoundary presenter = new MultiplayerOutputBoundary() {
            @Override
            public void prepareTimeoutView() {
                System.out.println("timeout");
            }

            @Override
            public void prepareGame(MultiplayerOutputData multiplayerOutputData) {
                System.out.println(multiplayerOutputData.getSeed() + " " + multiplayerOutputData.getSocket());

                Scanner s = new Scanner(System.in);

                String str = s.nextLine();

                try {
                    DataOutputStream out = new DataOutputStream(multiplayerOutputData.getSocket().getOutputStream());
                    out.writeUTF(str);
                    out.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                try {
                    DataInputStream in = new DataInputStream(multiplayerOutputData.getSocket().getInputStream());
                    System.out.println(in.readUTF());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        MultiplayerInteractor multiplayerInteractor = new MultiplayerInteractor("localhost",5555, presenter);

        MultiplayerController multiplayerController1 = new MultiplayerController(multiplayerInteractor);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewManager viewManager = new ViewManager(viewManagerModel);
        GameViewModel viewModel = new GameViewModel();
        GamePresenter gamePresenter = new GamePresenter(viewModel, viewManagerModel);
        GameInteractor interactor = new GameInteractor(new DataAccessMock(), gamePresenter);
        GameController controller = new GameController(interactor);

        viewManager.add("game", new GamePage(viewManager, viewModel, controller));
        viewManager.add("start", new StartMultiplayerGame(viewManager, multiplayerController1));

        viewManager.navigate("start");
        viewManager.setVisible(true);
        viewManager.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewManager.setLocationRelativeTo(null);
    }
}
