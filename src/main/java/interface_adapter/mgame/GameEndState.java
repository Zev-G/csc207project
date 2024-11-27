package interface_adapter.mgame;

import interface_adapter.game.GameState;

public class GameEndState {

    private int user;

    private int opp;

    public GameEndState(int user, int opp){
        this.user = user;
        this.opp = opp;
    }

    public int getUser() {
        return user;
    }

    public int getOpp() {
        return opp;
    }
}
