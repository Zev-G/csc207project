package interface_adapter.mgame;

import interface_adapter.ViewManagerModel;
import interface_adapter.game.GamePresenter;
import interface_adapter.game.GameViewModel;
import use_case.game.GameOutputData;
import use_case.mgame.MGameOutputBoundary;
import use_case.mgame.MGameOutputData;

public class MGamePresenter extends GamePresenter implements MGameOutputBoundary {
    public MGamePresenter(GameViewModel gameViewModel, ViewManagerModel viewManagerModel) {
        super(gameViewModel, viewManagerModel);
    }

    @Override
    public void waitForRespond() {
        System.out.println("waiting");
    }

    @Override
    public void PrepareError() {
        System.out.println("error");
    }

    @Override
    public void endGame(GameOutputData gameOutputData) {
        super.endGame(gameOutputData);
        System.out.println(((MGameOutputData) gameOutputData).getOpponentScore());
    }
}
