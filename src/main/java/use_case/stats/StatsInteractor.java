package use_case.stats;

import data_access.StatsDataAccess;

public class StatsInteractor implements StatsInputBoundary {
    private final FirebaseStatsDataAccess dataAccess;
    private final StatsOutputBoundary outputBoundary;

    public StatsInteractor(FirebaseStatsDataAccess dataAccess, StatsOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void fetchStats(StatsInputData inputData) {
        // Retrieve the username from the input data
        String username = inputData.getUsername();

        // Fetch user statistics from the data access layer
        StatsOutputData outputData = dataAccess.getUserStats(username);

        // Pass the retrieved data to the presenter via the output boundary
        outputBoundary.presentStats(outputData);
    }
}
