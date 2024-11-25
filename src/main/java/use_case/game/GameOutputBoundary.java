package use_case.game;

public interface GameOutputBoundary {

    void handleGuess(GameOutputData gameOutputData);

    void init(GameOutputData gameOutputData);
}
