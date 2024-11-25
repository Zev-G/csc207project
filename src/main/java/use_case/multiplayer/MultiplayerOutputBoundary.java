package use_case.multiplayer;

/**
 * This represents a multiplayer game output boundary.
 */
public interface MultiplayerOutputBoundary {

    /**
     * To prepare the timeout page.
     */
    void prepareTimeoutView();

    /**
     * To prepare the error page.
     */
    void prepareErrorView();

}
