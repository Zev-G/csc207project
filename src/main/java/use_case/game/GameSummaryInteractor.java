package use_case.game;

import use_case.game.GameSummaryInputData;
import use_case.game.GameSummaryOutputBoundary;
import use_case.game.GameSummaryOutputData;

public class GameSummaryInteractor implements GameSummaryInputBoundary{
    private final GameSummaryOutputBoundary outputBoundary;

    public GameSummaryInteractor(GameSummaryOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void fetchStats(GameSummaryInputData inputData) {
        // Fetch user statistics from the data access layer
        GameSummaryOutputData outputData = new GameSummaryOutputData(inputData.getGuessBar(), inputData.getPoints(), inputData.getUsername());

        // Pass the retrieved data to the presenter via the output boundary
        outputBoundary.presentStats(outputData);
    }
}
