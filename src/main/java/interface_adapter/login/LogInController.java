package interface_adapter.login;

import use_case.login.LogInInputBoundary;
import use_case.login.LogInInputData;

/**
 * The controller for handling log-in requests.
 */
public class LogInController {
    private final LogInInputBoundary interactor;

    public LogInController(LogInInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void handleLogIn(String username, String email, String password) {
        LogInInputData inputData = new LogInInputData(username, email, password);
        interactor.logIn(inputData);
    }
}
