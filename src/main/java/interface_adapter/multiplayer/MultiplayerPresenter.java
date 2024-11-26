package interface_adapter.multiplayer;

import use_case.multiplayer.MultiplayerOutputBoundary;
import view.pages.MultiplayerPage;

public class MultiplayerPresenter implements MultiplayerOutputBoundary {

    private MultiplayerViewModel viewModel;

    public MultiplayerPresenter(MultiplayerViewModel viewModel){
        this.viewModel = viewModel;
    }
    @Override
    public void prepareTimeoutView() {
        viewModel.setState("timeout");
    }

    @Override
    public void prepareErrorView() {
        viewModel.setState("error");
    }
}
