package view.pages;

import data_access.DataAccessMock;
import interface_adapter.ViewManagerModel;
import interface_adapter.game.GameController;
import interface_adapter.game.GamePresenter;
import interface_adapter.game.GameViewModel;
import use_case.game.GameInteractor;
import view.components.standard.RoundedButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGame extends Page{

    GameController gameController;
    protected StartGame(PageManager pageManager, GameController gameController) {
        super(pageManager);
        this.gameController = gameController;
        RoundedButton button = new RoundedButton("Start");

        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.init();
            }
        });

    }

    public static void main(String[] args) {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewManager viewManager = new ViewManager(viewManagerModel);
        GameViewModel viewModel = new GameViewModel();
        GamePresenter presenter = new GamePresenter(viewModel, viewManagerModel);
        GameInteractor interactor = new GameInteractor(new DataAccessMock(), presenter);
        GameController controller = new GameController(interactor);

        viewManager.add("game", new GamePageNew(viewManager, viewModel, controller));
        viewManager.add("start", new StartGame(viewManager, controller));

        viewManager.navigate("start");
        viewManager.setVisible(true);
        viewManager.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewManager.setLocationRelativeTo(null);
    }
}
