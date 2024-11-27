package interface_adapter.game;

import use_case.game.GameInputBoundary;
import use_case.game.GameInputData;
import use_case.game.GameSummaryInputBoundary;
import use_case.game.GameSummaryInputData;
import use_case.stats.StatsInputData;

import java.util.List;

public class GameSummaryController {

    final GameSummaryInputBoundary gameInteractor;

    public GameSummaryController(GameSummaryInputBoundary gameInteractor) {
        this.gameInteractor = gameInteractor;
    }

    public void fetchGameStats(List<Boolean> guessBar, int points, String username) {
        GameSummaryInputData inputData = new GameSummaryInputData(guessBar, points, username);
        gameInteractor.fetchStats(inputData);
    }
}
