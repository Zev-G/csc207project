package use_case.stats;

public class UpdateStatsInputData {
    private final String userName;
    private final int points;
    private final int correctGuesses;

    public UpdateStatsInputData(String userName, int points, int correctGuesses) {
        this.userName = userName;
        this.points = points;
        this.correctGuesses = correctGuesses;
    }

    public String getUserName() {
        return userName;
    }

    public int getPoints() {
        return points;
    }

    public int getCorrectGuesses() {
        return correctGuesses;
    }
}