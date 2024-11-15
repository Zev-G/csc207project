package interface_adapter.leaderboard;

import entity.DummyUserStats;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LeaderboardState {

    private final List<DummyUserStats> userStats = new ArrayList<>();

    public LeaderboardState(Collection<DummyUserStats> stats) {
        userStats.addAll(stats);
        Collections.sort(userStats);
    }

    /**
     * @param pos the position on the leaderboard, starting from one you want to check
     * @return the stats at that point.
     */
    public DummyUserStats getPosition(int pos) {
        if (pos < 0 || pos >= userStats.size()) throw new IndexOutOfBoundsException();
        return userStats.get(pos);
    }

}
