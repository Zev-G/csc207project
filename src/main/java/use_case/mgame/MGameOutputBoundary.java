package use_case.mgame;

import use_case.game.GameOutputBoundary;

public interface MGameOutputBoundary extends GameOutputBoundary {

    void waitForRespond();

    void PrepareError();

}
