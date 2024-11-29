package interface_adapter.stats;

import use_case.stats.StatsOutputData;

public class StatsPageState {

    private final String username;
    private final int points;
    private final int gamesPlayed;
    private final int correctGuesses;

    public StatsPageState(String username, int points, int gamesPlayed, int correctGuesses) {
        this.username = username;
        this.points = points;
        this.gamesPlayed = gamesPlayed;
        this.correctGuesses = correctGuesses;
    }

    // Factory method to create StatsPageState from StatsOutputData
    public static StatsPageState fromStatsOutputData(StatsOutputData outputData) {
        return new StatsPageState(
                outputData.getUsername(),
                outputData.getPoints(),
                outputData.getGamesPlayed(),
                outputData.getCorrectGuesses()
        );
    }

    // Builder for optional flexibility in creating the state
    public static class Builder {
        private String username;
        private int points;
        private int gamesPlayed;
        private int correctGuesses;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPoints(int points) {
            this.points = points;
            return this;
        }

        public Builder setGamesPlayed(int gamesPlayed) {
            this.gamesPlayed = gamesPlayed;
            return this;
        }

        public Builder setCorrectGuesses(int correctGuesses) {
            this.correctGuesses = correctGuesses;
            return this;
        }

        public StatsPageState build() {
            return new StatsPageState(username, points, gamesPlayed, correctGuesses);
        }
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return points;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getCorrectGuesses() {
        return correctGuesses;
    }

    @Override
    public String toString() {
        return String.format(
                "User: %s, Points: %d, Games Played: %d, Correct Guesses: %d",
                username, points, gamesPlayed, correctGuesses
        );
    }
}
