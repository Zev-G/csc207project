package app;

import data_access.UserStatsRepository;
import entity.UserStats;

import java.util.concurrent.CompletableFuture;

/**
 * Use case for fetching user stats from the repository.
 */
public class GetUserStatsUseCase {

    private final UserStatsRepository repository;

    /**
     * Constructor to inject the repository dependency.
     *
     * @param repository the data source for user stats
     */
    public GetUserStatsUseCase(UserStatsRepository repository) {
        this.repository = repository;
    }

    /**
     * Fetches the stats for a given username asynchronously.
     *
     * @param username the username of the user whose stats are to be fetched
     * @return a CompletableFuture containing the UserStats object
     */
    public CompletableFuture<UserStats> getUserStats(String username) {
        return repository.getStatsByUsername(username);
    }
}
