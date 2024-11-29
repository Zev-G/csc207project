package interface_adapter.game;

import use_case.game.GameInputBoundary;
import use_case.game.GameInputData;
import use_case.game.GameSummaryInputBoundary;
import use_case.game.GameSummaryInputData;
import use_case.stats.StatsInputData;
import use_case.stats.UpdateStatsInputBoundary;
import use_case.stats.UpdateStatsInputData;

import java.util.List;

/**
 * Controller class for managing game summary and statistics updates.
 */
public class GameSummaryController {

    final GameSummaryInputBoundary gameInteractor;
    private final UpdateStatsInputBoundary interactor;

    /**
     * Constructs a new GameSummaryController.
     *
     * @param interactor     The UpdateStatsInputBoundary used for updating statistics.
     * @param gameInteractor The GameSummaryInputBoundary used for fetching game statistics.
     */
    public GameSummaryController(UpdateStatsInputBoundary interactor, GameSummaryInputBoundary gameInteractor) {
        this.gameInteractor = gameInteractor;
        this.interactor = interactor;
    }

    /**
     * Fetches game statistics and updates the user's stats.
     *
     * @param guessBar       A list of boolean values representing the guess bar.
     * @param points         The number of points earned in the game.
     * @param username       The username of the player.
     * @param correctGuesses The number of correct guesses made by the player.
     */
    public void fetchGameStats(List<Boolean> guessBar, int points, String username, int correctGuesses) {
        GameSummaryInputData inputData = new GameSummaryInputData(guessBar, points, username);
        gameInteractor.fetchStats(inputData);
        updateStats(username, points, correctGuesses);
    }

    /**
     * Updates the user's statistics.
     *
     * @param userName       The username of the player.
     * @param points         The number of points earned in the game.
     * @param correctGuesses The number of correct guesses made by the player.
     */
    public void updateStats(String userName, int points, int correctGuesses) {
        // Create the input data
        UpdateStatsInputData inputData = new UpdateStatsInputData(userName, points, correctGuesses);

        // Call the interactor
        interactor.updateStats(inputData);
    }
}
