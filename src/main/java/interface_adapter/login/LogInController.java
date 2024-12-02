/**
 * Controller for handling log-in requests.
 * Acts as the intermediary between the view and the use case interactor.
 */
package interface_adapter.login;

import use_case.login.LogInInputBoundary;
import use_case.login.LogInInputData;

public class LogInController {
    private final LogInInputBoundary interactor;

    /**
     * Constructs a LogInController with the specified interactor.
     *
     * @param interactor The log-in use case interactor.
     */
    public LogInController(LogInInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Handles a log-in request by creating input data and passing it to the interactor.
     *
     * @param username The username entered by the user.
     * @param email    The email entered by the user.
     * @param password The password entered by the user.
     */
    public void handleLogIn(String username, String email, String password) {
        LogInInputData inputData = new LogInInputData(username, email, password);
        interactor.logIn(inputData);
    }
}
