package interface_adapter.game;

import use_case.game.GameSummaryOutputData;

import java.util.List;

public class GameSummaryPageState {

    private final String username;
    private final int points;
    private final List<Boolean> guessBar;

    public GameSummaryPageState(List<Boolean> guessBar, int points, String username) {
        this.username = username;
        this.points = points;
        this.guessBar = guessBar;
    }

    // Factory method to create GameSummaryPageState from GameSummaryOutputData
    public static GameSummaryPageState fromStatsOutputData(GameSummaryOutputData outputData) {
        return new GameSummaryPageState(
                outputData.getGuesses(),
                outputData.getPoints(),
                outputData.getUsername()
        );
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Getter for points
    public int getPoints() {
        return points;
    }

    // Getter for guessBar
    public List<Boolean> getGuessBar() {
        return guessBar;
    }
}
