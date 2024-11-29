package interface_adapter.game;

import use_case.game.GameSummaryOutputData;

import java.util.List;

/**
 * Represents the state of the game summary page, including the username, points, and guess bar.
 */
public class GameSummaryPageState {

    private final String username;
    private final int points;
    private final List<Boolean> guessBar;

    /**
     * Constructs a GameSummaryPageState with the given guess bar, points, and username.
     *
     * @param guessBar a list of booleans representing the guess bar state
     * @param points   the total points scored
     * @param username the username of the player
     */
    public GameSummaryPageState(List<Boolean> guessBar, int points, String username) {
        this.username = username;
        this.points = points;
        this.guessBar = guessBar;
    }

    /**
     * Creates a GameSummaryPageState instance from GameSummaryOutputData.
     *
     * @param outputData the output data containing game summary details
     * @return a new instance of GameSummaryPageState
     */
    public static GameSummaryPageState fromStatsOutputData(GameSummaryOutputData outputData) {
        return new GameSummaryPageState(
                outputData.getGuesses(),
                outputData.getPoints(),
                outputData.getUsername()
        );
    }

    /**
     * Returns the username of the player.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the total points scored by the player.
     *
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns the state of the guess bar.
     *
     * @return a list of booleans representing the guess bar
     */
    public List<Boolean> getGuessBar() {
        return guessBar;
    }
}
