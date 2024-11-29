package use_case.dataAccessInterface;

import use_case.stats.StatsOutputData;

public interface StatsDataAccess {
    StatsOutputData getUserStats(String username);
}
