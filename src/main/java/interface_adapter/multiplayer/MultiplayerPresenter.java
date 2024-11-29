package interface_adapter.multiplayer;

import use_case.multiplayer.MultiplayerOutputBoundary;
import view.pages.MultiplayerPage;

/**
 * The MultiplayerPresenter acts as the presenter in the MVP architecture for the multiplayer feature.
 * It updates the associated view model's state based on the use case responses.
 */
public class MultiplayerPresenter implements MultiplayerOutputBoundary {

    private MultiplayerViewModel viewModel;

    /**
     * Constructs a MultiplayerPresenter with the specified view model.
     *
     * @param viewModel the view model to be updated by this presenter
     */
    public MultiplayerPresenter(MultiplayerViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Prepares the view model for a timeout scenario.
     */
    @Override
    public void prepareTimeoutView() {
        viewModel.setState("timeout");
    }

    /**
     * Prepares the view model for an error scenario.
     */
    @Override
    public void prepareErrorView() {
        viewModel.setState("error");
    }
}
