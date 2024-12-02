package interface_adapter.login;

/**
 * Represents the state of the log-in process.
 */
public class LogInState {
    private boolean isLoggingIn;
    private boolean success;
    private String message;

    public LogInState(boolean isLoggingIn, boolean success, String message) {
        this.isLoggingIn = isLoggingIn;
        this.success = success;
        this.message = message;
    }

    public boolean isLoggingIn() {
        return isLoggingIn;
    }

    public void setLoggingIn(boolean isLoggingIn) {
        this.isLoggingIn = isLoggingIn;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
