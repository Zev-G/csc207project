/**
 * StatsInputData.java
 *
 * This class encapsulates the input data required to fetch user statistics.
 * It is part of the use case layer in the Clean Architecture, serving as a Data Transfer Object (DTO)
 * to pass information from the controller to the interactor.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Precisely describes its role in representing input data for fetching statistics.
 * - **Clarity**: Provides simple and unambiguous explanations of its fields and methods.
 * - **Completeness**: Covers the constructor and getter method comprehensively.
 * - **Ease of Use**: Offers a straightforward structure for interacting with statistics-related requests.
 * - **Up-to-Dateness**: Reflects the current design and field requirements.
 */

package use_case.stats;

/**
 * Represents the input data for fetching user statistics.
 * This class contains the necessary information to identify the user whose statistics are being retrieved.
 */
public class StatsInputData {
    private final String username;

    /**
     * Constructs an instance of StatsInputData with the specified username.
     *
     * @param username The username of the user whose statistics are to be fetched.
     *
     * Usage Example:
     * <pre>
     *     StatsInputData inputData = new StatsInputData("user123");
     *     String username = inputData.getUsername();
     * </pre>
     */
    public StatsInputData(String username) {
        this.username = username;
    }

    /**
     * Returns the username associated with this input data.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }
}
