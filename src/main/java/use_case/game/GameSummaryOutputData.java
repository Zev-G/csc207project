package use_case.game;

import java.util.List;

/**
 * Represents the output data for a game summary, including user details,
 * points scored, and the correctness of guesses.
 */
public class GameSummaryOutputData {

    private final String username;
    private final int points;
    private final List<Boolean> guessBar;

    /**
     * Constructs a GameSummaryOutputData object.
     *
     * @param guessBar a list of Boolean values representing the correctness of guesses
     * @param points   the total points scored in the game
     * @param username the username of the player
     */
    public GameSummaryOutputData(List<Boolean> guessBar, int points, String username) {
        this.username = username;
        this.points = points;
        this.guessBar = guessBar;
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
     * Returns the total points scored in the game.
     *
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns the list of guesses represented as Booleans.
     *
     * @return the list of guesses
     */
    public List<Boolean> getGuesses() {
        return guessBar;
    }
}
