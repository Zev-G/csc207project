package interface_adapter.leaderboard;

import entity.DummyUserStats;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Represents the state of the leaderboard, maintaining a sorted list of user statistics.
 */
public class LeaderboardState {

    private final List<DummyUserStats> userStats = new ArrayList<>();

    /**
     * Constructs a new LeaderboardState instance by taking a collection of user statistics.
     * The statistics are sorted as part of the initialization.
     *
     * @param stats a collection of DummyUserStats representing user statistics to initialize the leaderboard.
     */
    public LeaderboardState(Collection<DummyUserStats> stats) {
        userStats.addAll(stats);
        Collections.sort(userStats);
    }

    /**
     * Retrieves the user statistics at a specified position on the leaderboard.
     *
     * @param pos the position on the leaderboard, starting from 0 (zero-based index).
     * @return the user statistics at the specified position.
     * @throws IndexOutOfBoundsException if the position is out of bounds.
     */
    public DummyUserStats getPosition(int pos) {
        if (pos < 0 || pos >= userStats.size()) {
            throw new IndexOutOfBoundsException("Position is out of leaderboard bounds.");
        }
        return userStats.get(pos);
    }
}
