package use_case.stats;

/**
 * Repository interface for updating user statistics.
 */
public interface StatsRepository {

    /**
     * Updates the user's statistics with the provided data.
     *
     * @param userName       the username of the user
     * @param points         the points to add to the user's total
     * @param correctGuesses the number of correct guesses to add to the user's total
     */
    void updateStats(String userName, int points, int correctGuesses);
}
