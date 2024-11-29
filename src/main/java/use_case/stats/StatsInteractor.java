package use_case.stats;

import use_case.dataAccessInterface.StatsDataAccess;

/**
 * Interactor for handling user statistics retrieval.
 * Communicates with the data access layer and delegates presentation to the output boundary.
 */
public class StatsInteractor implements StatsInputBoundary {

    private final StatsDataAccess dataAccess;
    private final StatsOutputBoundary outputBoundary;

    /**
     * Constructs a StatsInteractor.
     *
     * @param dataAccess     the data access interface for retrieving user statistics
     * @param outputBoundary the output boundary for presenting the statistics
     */
    public StatsInteractor(StatsDataAccess dataAccess, StatsOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Fetches user statistics based on the provided input data.
     * Retrieves statistics from the data access layer and passes them to the output boundary.
     *
     * @param inputData the input data containing the username for fetching statistics
     */
    @Override
    public void fetchStats(StatsInputData inputData) {
        String username = inputData.getUsername();
        StatsOutputData outputData = dataAccess.getUserStats(username);
        outputBoundary.presentStats(outputData);
    }
}
