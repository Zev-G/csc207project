package entity;

import java.util.ArrayList;

public class GameStates {

    private ArrayList<GameRound> gameRounds;

    private int score;

    public GameStates() {
        gameRounds = new ArrayList<>();
    }

    public void add(GameRound g) {
        gameRounds.add(g);
    }

    public void setScore(int s) {
        score = s;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<GameRound> getGameRounds() {
        return gameRounds;
    }

    public void clean() {
        score = 0;
        gameRounds = new ArrayList<>();
    }

    public int getRounds() {
        return gameRounds.size();
    }

}
