package use_case.game;

import java.util.List;

public class GameSummaryInputData {
    private final String username;
    private final List<Boolean> guessBar;
    private final int points;

    public GameSummaryInputData(List<Boolean> guessBar, int points, String username) {
        this.guessBar = guessBar;
        this.points = points;
        this.username = username;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Getter for guessBar
    public List<Boolean> getGuessBar() {
        return guessBar;
    }

    // Getter for points
    public int getPoints() {
        return points;
    }
}
