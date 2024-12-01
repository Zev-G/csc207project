package interface_adapter.login;

import use_case.login.LogInOutputBoundary;
import use_case.login.LogInOutputData;

/**
 * The presenter for the log-in process.
 */
public class LogInPresenter implements LogInOutputBoundary {
    private final LogInViewModel viewModel;

    public LogInPresenter(LogInViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(LogInOutputData outputData) {
        LogInState state = new LogInState(false, outputData.isSuccess(), outputData.getMessage());
        viewModel.setState(state);
    }
}
