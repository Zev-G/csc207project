package entity;

import org.jetbrains.annotations.NotNull;

public class UserStats implements Comparable<UserStats> {

    private final String user;
    private int points;
    private int numGames;
    private int goodGuesses;

    // Constructor
    public UserStats(String user, int points, int numGames, int goodGuesses) {
        this.user = user;
        this.points = points;
        this.numGames = numGames;
        this.goodGuesses = goodGuesses;
    }

    // Getter for `user`
    public String getUser() {
        return user;
    }

    // Getter for `points`
    public int getPoints() {
        return points;
    }

    // Setter for `points`
    public void setPoints(int points) {
        this.points = points;
    }

    // Getter for `numGames`
    public int getNumGames() {
        return numGames;
    }

    // Setter for `numGames`
    public void setNumGames(int numGames) {
        this.numGames = numGames;
    }

    // Getter for `numGames`
    public int getGoodGuesses() {
        return goodGuesses;
    }

    // Setter for `numGames`
    public void setGoodGuesses(int goodGuesses) {
        this.goodGuesses = goodGuesses;
    }

    // Corrected `compareTo` method
    @Override
    public int compareTo(@NotNull UserStats o) {
        return Integer.compare(this.points, o.points);
    }

    @Override
    public String toString() {
        return "UserStats{" +
                "user='" + user + '\'' +
                ", points=" + points +
                ", numGames=" + numGames +
                '}';
    }
}
