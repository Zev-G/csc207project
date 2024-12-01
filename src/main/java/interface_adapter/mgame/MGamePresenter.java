package interface_adapter.mgame;

import interface_adapter.ViewManagerModel;
import interface_adapter.game.GamePresenter;
import interface_adapter.game.GameState;
import interface_adapter.game.GameViewModel;
import use_case.game.GameOutputData;
import use_case.mgame.MGameOutputBoundary;
import use_case.mgame.MGameOutputData;

/**
 * Presenter for a multiplayer game. Extends the functionality of the base GamePresenter
 * to handle multiplayer-specific actions and states.
 */
public class MGamePresenter extends GamePresenter implements MGameOutputBoundary {

    private MGameEndViewModel mGameEndViewModel;

    /**
     * Constructs a multiplayer game presenter.
     *
     * @param gameViewModel    the view model for the game state
     * @param viewManagerModel the manager for handling view states
     * @param mGameEndViewModel the view model for multiplayer game end state
     */
    public MGamePresenter(GameViewModel gameViewModel, ViewManagerModel viewManagerModel, MGameEndViewModel mGameEndViewModel) {
        super(gameViewModel, viewManagerModel);
        this.mGameEndViewModel = mGameEndViewModel;
    }

    /**
     * Initializes the multiplayer game by setting up the initial state.
     *
     * @param gameOutputData the data required to initialize the game
     */
    @Override
    public void init(GameOutputData gameOutputData) {
        viewManagerModel.setState("mgame");
        super.init(gameOutputData);
    }

    /**
     * Sets the view state to indicate the game is waiting for a response.
     */
    @Override
    public void waitForResponse() {
        viewManagerModel.setState("wait");
    }

    /**
     * Prepares an error state for the view.
     */
    @Override
    public void PrepareError() {
        viewManagerModel.setState("error");
    }

    /**
     * Ends the multiplayer game and updates the view with the final scores and results.
     *
     * @param gameOutputData the data containing game results and scores
     */
    @Override
    public void endGame(GameOutputData gameOutputData) {
        gameViewModel.setState(new GameState(gameOutputData.isAcceptable(), gameOutputData.getScore(),
                gameOutputData.getNextPhoto(), gameOutputData.getPhotoID(), gameOutputData.getTarget(),
                gameOutputData.getRound(), true));
        mGameEndViewModel.setState(new GameEndState(((MGameOutputData) gameOutputData).getScore(),
                ((MGameOutputData) gameOutputData).getOpponentScore()));
        viewManagerModel.setState("endmgame");
    }
}
