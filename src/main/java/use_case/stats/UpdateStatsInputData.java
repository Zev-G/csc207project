package use_case.stats;

/**
 * Represents input data for updating user statistics.
 */
public class UpdateStatsInputData {

    private final String userName;
    private final int points;
    private final int correctGuesses;

    /**
     * Constructs an UpdateStatsInputData object.
     *
     * @param userName       the username of the user
     * @param points         the points to add to the user's total
     * @param correctGuesses the number of correct guesses to add to the user's total
     */
    public UpdateStatsInputData(String userName, int points, int correctGuesses) {
        this.userName = userName;
        this.points = points;
        this.correctGuesses = correctGuesses;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns the points to add to the user's total.
     *
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns the number of correct guesses to add to the user's total.
     *
     * @return the correct guesses
     */
    public int getCorrectGuesses() {
        return correctGuesses;
    }
}
