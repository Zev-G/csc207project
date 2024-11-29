package use_case.stats;

/**
 * Represents the output data for user statistics, including points, games played, and correct guesses.
 */
public class StatsOutputData {

    private final String username;
    private final int points;
    private final int gamesPlayed;
    private final int correctGuesses;

    /**
     * Constructs a StatsOutputData object.
     *
     * @param username       the username of the user
     * @param points         the total points scored by the user
     * @param gamesPlayed    the total number of games played by the user
     * @param correctGuesses the total number of correct guesses made by the user
     */
    public StatsOutputData(String username, int points, int gamesPlayed, int correctGuesses) {
        this.username = username;
        this.points = points;
        this.gamesPlayed = gamesPlayed;
        this.correctGuesses = correctGuesses;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the total points scored by the user.
     *
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns the total number of games played by the user.
     *
     * @return the games played
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Returns the total number of correct guesses made by the user.
     *
     * @return the correct guesses
     */
    public int getCorrectGuesses() {
        return correctGuesses;
    }
}
