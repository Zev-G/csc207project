package data_access;

import use_case.stats.StatsOutputData;

public interface StatsDataAccess {
    StatsOutputData getUserStats(String username);
}
