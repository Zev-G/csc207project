package interface_adapter.stats;

import use_case.stats.StatsOutputData;

/**
 * Represents the state of the stats page, containing user-related statistics.
 */
public class StatsPageState {

    private final String username;
    private final int points;
    private final int gamesPlayed;
    private final int correctGuesses;

    /**
     * Constructs a StatsPageState with the provided user statistics.
     *
     * @param username       the username of the player
     * @param points         the total points earned by the player
     * @param gamesPlayed    the number of games played
     * @param correctGuesses the number of correct guesses
     */
    public StatsPageState(String username, int points, int gamesPlayed, int correctGuesses) {
        this.username = username;
        this.points = points;
        this.gamesPlayed = gamesPlayed;
        this.correctGuesses = correctGuesses;
    }

    /**
     * Factory method to create a StatsPageState from a StatsOutputData object.
     *
     * @param outputData the source data for user statistics
     * @return a new StatsPageState instance
     */
    public static StatsPageState fromStatsOutputData(StatsOutputData outputData) {
        return new StatsPageState(
                outputData.getUsername(),
                outputData.getPoints(),
                outputData.getGamesPlayed(),
                outputData.getCorrectGuesses()
        );
    }

    /**
     * Builder class for creating a StatsPageState with optional flexibility.
     */
    public static class Builder {
        private String username;
        private int points;
        private int gamesPlayed;
        private int correctGuesses;

        /**
         * Sets the username for the stats state.
         *
         * @param username the username of the player
         * @return the Builder instance
         */
        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        /**
         * Sets the points for the stats state.
         *
         * @param points the total points earned by the player
         * @return the Builder instance
         */
        public Builder setPoints(int points) {
            this.points = points;
            return this;
        }

        /**
         * Sets the games played count for the stats state.
         *
         * @param gamesPlayed the number of games played
         * @return the Builder instance
         */
        public Builder setGamesPlayed(int gamesPlayed) {
            this.gamesPlayed = gamesPlayed;
            return this;
        }

        /**
         * Sets the correct guesses count for the stats state.
         *
         * @param correctGuesses the number of correct guesses
         * @return the Builder instance
         */
        public Builder setCorrectGuesses(int correctGuesses) {
            this.correctGuesses = correctGuesses;
            return this;
        }

        /**
         * Builds a new StatsPageState with the specified attributes.
         *
         * @return a new StatsPageState instance
         */
        public StatsPageState build() {
            return new StatsPageState(username, points, gamesPlayed, correctGuesses);
        }
    }

    /**
     * Gets the username of the player.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the total points earned by the player.
     *
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Gets the number of games played.
     *
     * @return the games played count
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Gets the number of correct guesses.
     *
     * @return the correct guesses count
     */
    public int getCorrectGuesses() {
        return correctGuesses;
    }

    /**
     * Returns a string representation of the stats state.
     *
     * @return a formatted string of the user's statistics
     */
    @Override
    public String toString() {
        return String.format(
                "User: %s, Points: %d, Games Played: %d, Correct Guesses: %d",
                username, points, gamesPlayed, correctGuesses
        );
    }
}
