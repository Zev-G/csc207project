package entity;

import java.util.ArrayList;

/**
 * This class represents the game state for the whole game.
 */
public class GameStates {
    private ArrayList<GameRound> gameRounds;
    private int score;

    /**
     * To make a new game state.
     */
    public GameStates() {
        gameRounds = new ArrayList<>();
    }

    /**
     * To add a game round to the game state.
     *
     * @param g a game round
     */
    public void add(GameRound g) {
        gameRounds.add(g);
    }

    /**
     * To set the score.
     *
     * @param s the score
     */
    public void setScore(int s) {
        score = s;
    }

    /**
     * To get the score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * To get all game rounds.
     *
     * @return game rounds
     */
    public ArrayList<GameRound> getGameRounds() {
        return gameRounds;
    }

    /**
     * To clear the current instance.
     */
    public void clean() {
        score = 0;
        gameRounds = new ArrayList<>();
    }

    /**
     * To get the next round.
     *
     * @return next round.
     */
    public int getRounds() {
        return gameRounds.size();
    }

}
