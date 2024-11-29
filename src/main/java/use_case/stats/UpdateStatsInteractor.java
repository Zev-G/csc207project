/**
 * UpdateStatsInteractor.java
 *
 * This class implements the use case logic for updating user statistics.
 * It orchestrates the interaction between the controller and the repository,
 * adhering to the Clean Architecture principles by keeping business logic separate
 * from the data and presentation layers.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Documents its role in handling the update statistics use case.
 * - **Clarity**: Provides clear descriptions of its methods and responsibilities.
 * - **Completeness**: Explains the constructor, fields, and methods comprehensively.
 * - **Ease of Use**: Demonstrates its integration within the use case layer.
 * - **Up-to-Dateness**: Reflects the current implementation details.
 */

package use_case.stats;

/**
 * Interactor for the update statistics use case.
 * Implements the {@link UpdateStatsInputBoundary} to handle logic for updating user statistics.
 */
public class UpdateStatsInteractor implements UpdateStatsInputBoundary {

    private final StatsRepository statsRepository;

    /**
     * Constructs an UpdateStatsInteractor with the specified repository.
     *
     * @param statsRepository The repository responsible for persisting updated statistics.
     *
     * Usage Example:
     * <pre>
     *     StatsRepository repository = new StatsDatabaseAdapter(...);
     *     UpdateStatsInteractor interactor = new UpdateStatsInteractor(repository);
     *     interactor.updateStats(new UpdateStatsInputData("user123", 50, 10));
     * </pre>
     */
    public UpdateStatsInteractor(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    /**
     * Updates user statistics based on the provided input data.
     *
     * Process:
     * 1. Extracts the user details and statistics from the input data.
     * 2. Delegates the update operation to the repository.
     *
     * @param inputData The input data containing the user's details and statistics to update.
     */
    @Override
    public void updateStats(UpdateStatsInputData inputData) {
        statsRepository.updateStats(inputData.getUserName(), inputData.getPoints(), inputData.getCorrectGuesses());
    }
}
