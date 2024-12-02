/**
 * Presenter for the log-in process.
 * Updates the view model with the result of the log-in process and manages navigation on success.
 */
package interface_adapter.login;

import use_case.login.LogInOutputBoundary;
import use_case.login.LogInOutputData;
import view.pages.ViewManager;

public class LogInPresenter implements LogInOutputBoundary {
    private final LogInViewModel viewModel;
    private final ViewManager viewManager;

    /**
     * Constructs a LogInPresenter with the specified view model and view manager.
     *
     * @param viewModel   The ViewModel for managing log-in state.
     * @param viewManager The ViewManager for handling navigation.
     */
    public LogInPresenter(LogInViewModel viewModel, ViewManager viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }

    /**
     * Updates the view model with the log-in result.
     *
     * @param outputData The output data containing the result of the log-in process.
     */
    @Override
    public void present(LogInOutputData outputData) {
        LogInState state = new LogInState(false, outputData.isSuccess(), outputData.getMessage());
        viewModel.setState(state);
    }

    /**
     * Handles navigation to the main page upon a successful log-in.
     */
    @Override
    public void onLoginSuccess() {
        viewManager.navigate("main");
    }
}
