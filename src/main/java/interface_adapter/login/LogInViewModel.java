package interface_adapter.login;

/**
 * The ViewModel for the log-in process.
 */
public class LogInViewModel {
    private LogInState state;

    public LogInState getState() {
        return state;
    }

    public void setState(LogInState state) {
        this.state = state;
    }
}
