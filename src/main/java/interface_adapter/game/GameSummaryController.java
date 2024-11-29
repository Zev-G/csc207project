package interface_adapter.game;

import use_case.game.GameInputBoundary;
import use_case.game.GameInputData;
import use_case.game.GameSummaryInputBoundary;
import use_case.game.GameSummaryInputData;
import use_case.stats.StatsInputData;
import use_case.stats.UpdateStatsInputBoundary;
import use_case.stats.UpdateStatsInputData;

import java.util.List;

public class GameSummaryController {

    final GameSummaryInputBoundary gameInteractor;
    private final UpdateStatsInputBoundary interactor;

    public GameSummaryController(UpdateStatsInputBoundary interactor, GameSummaryInputBoundary gameInteractor) {

        this.gameInteractor = gameInteractor;
        this.interactor = interactor;
    }

    public void fetchGameStats(List<Boolean> guessBar, int points, String username, int correctGuesses) {
        GameSummaryInputData inputData = new GameSummaryInputData(guessBar, points, username);
        gameInteractor.fetchStats(inputData);
        updateStats(username, points, correctGuesses);
    }

    public void updateStats(String userName, int points, int correctGuesses) {
        // Create the input data
        UpdateStatsInputData inputData = new UpdateStatsInputData(userName, points, correctGuesses);

        // Call the interactor
        interactor.updateStats(inputData);
    }
}
