/**
 * UpdateStatsInputData.java
 *
 * This class encapsulates the input data required for the update statistics use case.
 * It is part of the use case layer in the Clean Architecture, serving as a Data Transfer Object (DTO)
 * to pass information from the controller to the interactor.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Precisely describes its role as input data for updating statistics.
 * - **Clarity**: Provides clear descriptions of its fields and methods.
 * - **Completeness**: Documents all fields, the constructor, and getter methods.
 * - **Ease of Use**: Offers a structured way to pass user statistics for updates.
 * - **Up-to-Dateness**: Reflects the current design and data requirements for updates.
 */

package use_case.stats;

/**
 * Represents the input data for updating user statistics.
 * This class holds the data necessary to update the user's points and correct guesses.
 */
public class UpdateStatsInputData {

    private final String userName;
    private final int points;
    private final int correctGuesses;

    /**
     * Constructs an instance of UpdateStatsInputData with the specified parameters.
     *
     * @param userName       The username of the user whose statistics are being updated.
     * @param points         The additional points to add to the user's total points.
     * @param correctGuesses The additional correct guesses to add to the user's total.
     *
     * Usage Example:
     * <pre>
     *     UpdateStatsInputData inputData = new UpdateStatsInputData("user123", 50, 10);
     *     System.out.println("User: " + inputData.getUserName());
     * </pre>
     */
    public UpdateStatsInputData(String userName, int points, int correctGuesses) {
        this.userName = userName;
        this.points = points;
        this.correctGuesses = correctGuesses;
    }

    /**
     * Returns the username of the user whose statistics are being updated.
     *
     * @return The username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns the additional points to add to the user's total points.
     *
     * @return The additional points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns the additional correct guesses to add to the user's total.
     *
     * @return The additional correct guesses.
     */
    public int getCorrectGuesses() {
        return correctGuesses;
    }
}
