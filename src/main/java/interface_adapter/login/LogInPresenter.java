package interface_adapter.login;

import use_case.login.LogInOutputBoundary;
import use_case.login.LogInOutputData;
import view.pages.ViewManager;

/**
 * The presenter for the log-in process.
 */
public class LogInPresenter implements LogInOutputBoundary {
    private final LogInViewModel viewModel;
    private final ViewManager viewManager;

    public LogInPresenter(LogInViewModel viewModel, ViewManager viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }

    @Override
    public void present(LogInOutputData outputData) {
        LogInState state = new LogInState(false, outputData.isSuccess(), outputData.getMessage());
        viewModel.setState(state);
    }

    @Override
    public void onLoginSuccess() {
        viewManager.navigate("main");
    }
}
