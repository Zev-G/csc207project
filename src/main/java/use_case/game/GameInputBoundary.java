package use_case.game;

public interface GameInputBoundary {

    void handleGuess(GameInputData input);

    void init();
}
