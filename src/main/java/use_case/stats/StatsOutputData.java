/**
 * StatsOutputData.java
 *
 * This class encapsulates the output data for the statistics use case.
 * It is part of the use case layer in the Clean Architecture, serving as a Data Transfer Object (DTO)
 * to pass processed statistics data from the interactor to the presenter or output boundary.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Describes its role as a container for user statistics output data.
 * - **Clarity**: Provides straightforward descriptions of its fields and methods.
 * - **Completeness**: Covers all fields, the constructor, and getter methods.
 * - **Ease of Use**: Offers a simple and structured way to access processed statistics data.
 * - **Up-to-Dateness**: Reflects the current structure and requirements for statistics output data.
 */

package use_case.stats;

public class StatsOutputData {

    /**
     * Represents the output data for the statistics use case.
     * This class holds the processed statistics for a specific user.
     */
    private final String username;
    private final int points;
    private final int gamesPlayed;
    private final int correctGuesses;

    /**
     * Constructs an instance of StatsOutputData with the specified parameters.
     *
     * @param username       The username of the user.
     * @param points         The total points accumulated by the user.
     * @param gamesPlayed    The total number of games played by the user.
     * @param correctGuesses The total number of correct guesses made by the user.
     *
     * Usage Example:
     * <pre>
     *     StatsOutputData outputData = new StatsOutputData("user123", 100, 5, 20);
     *     System.out.println("Username: " + outputData.getUsername());
     * </pre>
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
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the total points accumulated by the user.
     *
     * @return The total points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns the total number of games played by the user.
     *
     * @return The total number of games played.
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Returns the total number of correct guesses made by the user.
     *
     * @return The total number of correct guesses.
     */
    public int getCorrectGuesses() {
        return correctGuesses;
    }
}
