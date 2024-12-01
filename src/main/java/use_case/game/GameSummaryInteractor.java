package use_case.game;

import use_case.game.GameSummaryInputData;
import use_case.game.GameSummaryOutputBoundary;
import use_case.game.GameSummaryOutputData;

/**
 * Interactor for fetching and processing game summary statistics.
 * Communicates with the output boundary to present the summarized data.
 */
public class GameSummaryInteractor implements GameSummaryInputBoundary {

    private final GameSummaryOutputBoundary outputBoundary;

    /**
     * Constructs a GameSummaryInteractor.
     *
     * @param outputBoundary the output boundary to handle presentation of game summary data
     */
    public GameSummaryInteractor(GameSummaryOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    /**
     * Fetches game statistics based on the provided input data.
     * Constructs an output data object and delegates its presentation to the output boundary.
     *
     * @param inputData the input data containing game details
     */
    @Override
    public void fetchStats(GameSummaryInputData inputData) {
        GameSummaryOutputData outputData = new GameSummaryOutputData(
                inputData.getGuessBar(),
                inputData.getPoints(),
                inputData.getUsername()
        );

        outputBoundary.presentStats(outputData);
    }
}
