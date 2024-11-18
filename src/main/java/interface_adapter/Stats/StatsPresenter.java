package interface_adapter.Stats;

import entity.UserStats;

public class StatsPresenter {
    /**
     * Formats the user stats for display.
     *
     * @param stats the UserStats object
     * @return a formatted string
     */
    public String formatStats(UserStats stats) {
        return "User: " + stats.getUser() + "\n" +
                "Points: " + stats.getPoints() + "\n" +
                "Games: " + stats.getNumGames() + "\n" +
                "Good Guesses: " + stats.getGoodGuesses();
    }
}
