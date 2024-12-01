package use_case.stats;

/**
 * Interactor for updating user statistics.
 * Delegates the update operation to the stats repository.
 */
public class UpdateStatsInteractor implements UpdateStatsInputBoundary {

    private final StatsRepository statsRepository;

    /**
     * Constructs an UpdateStatsInteractor.
     *
     * @param statsRepository the repository responsible for updating user statistics
     */
    public UpdateStatsInteractor(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    /**
     * Updates user statistics based on the provided input data.
     *
     * @param inputData the input data containing the statistics update details
     */
    @Override
    public void updateStats(UpdateStatsInputData inputData) {
        statsRepository.updateStats(
                inputData.getUserName(),
                inputData.getPoints(),
                inputData.getCorrectGuesses()
        );
    }
}
