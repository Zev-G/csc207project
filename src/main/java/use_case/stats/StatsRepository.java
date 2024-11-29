/**
 * StatsRepository.java
 *
 * This interface defines the repository for handling persistence operations related to user statistics.
 * It adheres to Clean Architecture principles by decoupling the use case layer from the data layer,
 * allowing for flexible implementations of the underlying storage mechanism.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Clearly defines its role in persisting and updating user statistics.
 * - **Clarity**: Provides straightforward descriptions of its methods.
 * - **Completeness**: Covers all methods and their responsibilities.
 * - **Ease of Use**: Simplifies integration with the use case layer by abstracting data operations.
 * - **Up-to-Dateness**: Reflects the current requirements for updating statistics.
 */

package use_case.stats;

/**
 * Repository interface for managing user statistics persistence.
 * Defines the contract for updating user statistics in the data layer.
 */
public interface StatsRepository {

    /**
     * Updates the statistics for a specific user.
     *
     * Responsibilities:
     * - Modifies the user's total points.
     * - Increments the total number of correct guesses.
     *
     * @param userName       The username of the user whose statistics are being updated.
     * @param points         The additional points to add to the user's total points.
     * @param correctGuesses The additional correct guesses to add to the user's total.
     *
     * Usage Example:
     * <pre>
     *     StatsRepository repository = new StatsDatabaseAdapter(...);
     *     repository.updateStats("user123", 50, 10);
     * </pre>
     */
    void updateStats(String userName, int points, int correctGuesses);
}
