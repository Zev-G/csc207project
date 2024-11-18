package data_access;

import entity.UserStats;

import java.util.concurrent.CompletableFuture;

/**
 * Repository interface for fetching user stats.
 */
public interface UserStatsRepository {

    /**
     * Fetches the stats for a given username.
     *
     * @param username the username of the user
     * @return a CompletableFuture containing the UserStats object
     */
    CompletableFuture<UserStats> getStatsByUsername(String username);
}
