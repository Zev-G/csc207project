package interface_adapter.game;

import interface_adapter.ViewManagerModel;
import use_case.game.GameOutputBoundary;
import use_case.game.GameOutputData;
import use_case.mgame.MGameOutputData;

/**
 * The GamePresenter class implements the GameOutputBoundary interface and is responsible for
 * updating the game view model based on the output from the game use case.
 */
public class GamePresenter implements GameOutputBoundary {

    protected GameViewModel gameViewModel;

    protected ViewManagerModel viewManagerModel;

    /**
     * Constructs a new GamePresenter.
     *
     * @param gameViewModel    The view model for the game, which will be updated by this presenter.
     * @param viewManagerModel The view manager model, which manages the current view.
     */
    public GamePresenter(GameViewModel gameViewModel, ViewManagerModel viewManagerModel) {
        this.gameViewModel = gameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Handles the result of a guess made in the game.
     * Updates the game view model with the new state based on the guess outcome.
     *
     * @param gameOutputData The output data containing the result of the guess and updated game state.
     */
    @Override
    public void handleGuess(GameOutputData gameOutputData) {
        gameViewModel.setState(new GameState(gameOutputData.isAcceptable(), gameOutputData.getScore(),
                gameOutputData.getNextPhoto(), gameOutputData.getPhotoID(), gameOutputData.getTarget(),
                gameOutputData.getRound()));
    }

    /**
     * Initializes the game state.
     * Sets the initial state of the game in the view model.
     *
     * @param gameOutputData The output data containing the initial game state.
     */
    @Override
    public void init(GameOutputData gameOutputData) {
        gameViewModel.setState(new GameState(gameOutputData.isAcceptable(), gameOutputData.getScore(),
                gameOutputData.getNextPhoto(), gameOutputData.getPhotoID(), gameOutputData.getTarget(),
                gameOutputData.getRound()));
    }

    /**
     * Handles the end of the game.
     * Updates the game view model with the final state and marks the game as ended.
     *
     * @param gameOutputData The output data containing the final game state.
     */
    @Override
    public void endGame(GameOutputData gameOutputData) {
        gameViewModel.setState(new GameState(gameOutputData.isAcceptable(), gameOutputData.getScore(),
                gameOutputData.getNextPhoto(), gameOutputData.getPhotoID(), gameOutputData.getTarget(),
                gameOutputData.getRound(), true));
        System.out.println("END");
    }
}
