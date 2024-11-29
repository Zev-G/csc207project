package use_case.game;

import java.util.List;

public class GameSummaryOutputData {
    private final String username;
    private final int points;
    private final List<Boolean> guessBar;

    public GameSummaryOutputData(List<Boolean> guessBar, int points, String username) {
        this.username = username;
        this.points = points;
        this.guessBar = guessBar;
    }

    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return points;
    }

    public List<Boolean> getGuesses(){return guessBar;}
}
