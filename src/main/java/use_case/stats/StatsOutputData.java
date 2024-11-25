package use_case.stats;

public class StatsOutputData {
    private final int points;
    private final int gamesPlayed;
    private final int correctGuesses;

    public StatsOutputData(int points, int gamesPlayed, int correctGuesses) {
        this.points = points;
        this.gamesPlayed = gamesPlayed;
        this.correctGuesses = correctGuesses;
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
