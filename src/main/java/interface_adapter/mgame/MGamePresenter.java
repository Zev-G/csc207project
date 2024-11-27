package interface_adapter.mgame;

import interface_adapter.ViewManagerModel;
import interface_adapter.game.GamePresenter;
import interface_adapter.game.GameState;
import interface_adapter.game.GameViewModel;
import use_case.game.GameOutputData;
import use_case.mgame.MGameOutputBoundary;
import use_case.mgame.MGameOutputData;

/**
 * Multiplayer game presenter.
 */
public class MGamePresenter extends GamePresenter implements MGameOutputBoundary {

    private MGameEndViewModel mGameEndViewModel;

    public MGamePresenter(GameViewModel gameViewModel, ViewManagerModel viewManagerModel, MGameEndViewModel mGameEndViewModel) {
        super(gameViewModel, viewManagerModel);
        this.mGameEndViewModel = mGameEndViewModel;
    }

    @Override
    public void init(GameOutputData gameOutputData) {
        viewManagerModel.setState("mgame");
        super.init(gameOutputData);
    }

    @Override
    public void waitForResponse() {
        viewManagerModel.setState("wait");
    }

    @Override
    public void PrepareError() {
        viewManagerModel.setState("error");
    }

    @Override
    public void endGame(GameOutputData gameOutputData) {
        mGameEndViewModel.setState(new GameEndState(((MGameOutputData) gameOutputData).getScore(),
                ((MGameOutputData) gameOutputData).getOpponentScore()));
        viewManagerModel.setState("endmgame");

    }
}
