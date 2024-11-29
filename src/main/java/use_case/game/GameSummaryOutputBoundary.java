package use_case.game;

/**
 * Defines the output boundary for presenting game summary statistics.
 */
public interface GameSummaryOutputBoundary {

    /**
     * Presents the game summary statistics to the user.
     *
     * @param outputData the data containing the game summary details
     */
    void presentStats(GameSummaryOutputData outputData);
}
