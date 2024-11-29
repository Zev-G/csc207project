package use_case.game;

import java.util.List;

/**
 * Represents input data for summarizing a game, including user details,
 * guesses, and points scored.
 */
public class GameSummaryInputData {

    private final String username;
    private final List<Boolean> guessBar;
    private final int points;

    /**
     * Constructs a GameSummaryInputData object.
     *
     * @param guessBar a list of Boolean values representing the correctness of guesses
     * @param points   the total points scored in the game
     * @param username the username of the player
     */
    public GameSummaryInputData(List<Boolean> guessBar, int points, String username) {
        this.guessBar = guessBar;
        this.points = points;
        this.username = username;
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
     * Returns the list of guesses represented as Booleans.
     *
     * @return the guess bar
     */
    public List<Boolean> getGuessBar() {
        return guessBar;
    }

    /**
     * Returns the total points scored in the game.
     *
     * @return the points
     */
    public int getPoints() {
        return points;
    }
}
