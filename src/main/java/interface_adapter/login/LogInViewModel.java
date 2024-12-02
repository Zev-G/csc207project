package interface_adapter.login;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the log-in process, implementing ViewModel<LogInState>.
 * This class manages the state of the LogIn page and notifies listeners of changes.
 */
public class LogInViewModel extends ViewModel<LogInState> {

    /**
     * Constructs a LogInViewModel with the default view name "logInPage".
     * Sets the initial state for the log-in process.
     */
    public LogInViewModel() {
        super("logInPage");
        // Initial state with no login in progress
        setState(new LogInState(false, false, null));
    }

    /**
     * Updates the log-in state to reflect the current login process.
     *
     * @param success    whether the login was successful or not
     * @param message    the message to show (e.g., error or success message)
     * @param isLoggingIn whether the user is currently in the process of logging in
     */
    public void updateState(boolean success, String message, boolean isLoggingIn) {
        setState(new LogInState(isLoggingIn, success, message));
    }
}
