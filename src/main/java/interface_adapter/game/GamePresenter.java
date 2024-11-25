package interface_adapter.game;

import interface_adapter.ViewManagerModel;
import use_case.game.GameOutputBoundary;
import use_case.game.GameOutputData;
import use_case.mgame.MGameOutputData;

public class GamePresenter implements GameOutputBoundary {

    GameViewModel gameViewModel;

    ViewManagerModel viewManagerModel;

    public GamePresenter(GameViewModel gameViewModel, ViewManagerModel viewManagerModel) {
        this.gameViewModel = gameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void handleGuess(GameOutputData gameOutputData) {
        gameViewModel.setState(new GameState(gameOutputData.isAcceptable(), gameOutputData.getScore(),
                gameOutputData.getNextPhoto(), gameOutputData.getPhotoID(), gameOutputData.getTarget(),
                gameOutputData.getRound()));
    }

    @Override
    public void init(GameOutputData gameOutputData) {
        gameViewModel.setState(new GameState(gameOutputData.isAcceptable(), gameOutputData.getScore(),
                gameOutputData.getNextPhoto(), gameOutputData.getPhotoID(), gameOutputData.getTarget(),
                gameOutputData.getRound()));

        viewManagerModel.setState("game");
    }

    @Override
    public void endGame(GameOutputData gameOutputData) {
        System.out.println("END");
    }
}
