package use_case.stats;

public class UpdateStatsInteractor implements UpdateStatsInputBoundary {

    private final StatsRepository statsRepository;

    public UpdateStatsInteractor(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Override
    public void updateStats(UpdateStatsInputData inputData) {
        statsRepository.updateStats(inputData.getUserName(), inputData.getPoints(), inputData.getCorrectGuesses());
    }
}