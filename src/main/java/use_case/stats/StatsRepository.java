package use_case.stats;

public interface StatsRepository {
    void updateStats(String userName, int points, int correctGuesses);
}