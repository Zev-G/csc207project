package use_case.stats;

public class StatsOutputData {
    private final String username;
    private final int points;
    private final int gamesPlayed;
    private final int correctGuesses;

    public StatsOutputData(String username, int points, int gamesPlayed, int correctGuesses) {
        this.username = username;
        this.points = points;
        this.gamesPlayed = gamesPlayed;
        this.correctGuesses = correctGuesses;
    }

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
}
