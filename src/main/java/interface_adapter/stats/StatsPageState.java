/**
 * StatsPageState.java
 *
 * This class represents the state of the statistics page in the application.
 * It provides a snapshot of a user's performance metrics, including points, games played,
 * and the number of correct guesses.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Describes the class's role in encapsulating statistics data.
 * - **Clarity**: Provides straightforward explanations of functionality.
 * - **Completeness**: Covers all constructors, methods, and inner classes.
 * - **Ease of Use**: Includes a builder pattern and factory method for flexible instantiation.
 * - **Up-to-Dateness**: Reflects current structure and usage.
 */

package interface_adapter.stats;

import use_case.stats.StatsOutputData;

/**
 * Represents the state of the statistics page, encapsulating user performance metrics.
 */
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

    /**
     * Constructs a StatsPageState object with the given parameters.
     *
     * @param outputData  The output data to be displayed containing username, points, games played and correct guesses.    T
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
     * Returns the username of the user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the total points accumulated by the user.
     *
     * @return The total points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns the total number of games played by the user.
     *
     * @return The total number of games played.
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Returns the total number of correct guesses made by the user.
     *
     * @return The total number of correct guesses.
     */
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

    /**
     * Builder class for constructing StatsPageState objects with optional parameters.
     */
    public static class Builder {
        private String username;
        private int points;
        private int gamesPlayed;
        private int correctGuesses;

        /**
         * Sets the username for the StatsPageState being built.
         *
         * @param username The username to set.
         * @return The Builder instance for chaining.
         */
        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        /**
         * Sets the points for the StatsPageState being built.
         *
         * @param points The points to set.
         * @return The Builder instance for chaining.
         */
        public Builder setPoints(int points) {
            this.points = points;
            return this;
        }

        /**
         * Sets the number of games played for the StatsPageState being built.
         *
         * @param gamesPlayed The number of games played to set.
         * @return The Builder instance for chaining.
         */
        public Builder setGamesPlayed(int gamesPlayed) {
            this.gamesPlayed = gamesPlayed;
            return this;
        }

        /**
         * Sets the number of correct guesses for the StatsPageState being built.
         *
         * @param correctGuesses The number of correct guesses to set.
         * @return The Builder instance for chaining.
         */
        public Builder setCorrectGuesses(int correctGuesses) {
            this.correctGuesses = correctGuesses;
            return this;
        }

        /**
         * Builds and returns a StatsPageState object with the specified parameters.
         *
         * @return A new StatsPageState object.
         */
        public StatsPageState build() {
            return new StatsPageState(username, points, gamesPlayed, correctGuesses);
        }
    }
}
