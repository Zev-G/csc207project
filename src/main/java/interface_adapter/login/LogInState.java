package interface_adapter.login;

/**
 * Represents the state of the log-in process.
 */
public class LogInState {
    private boolean success;
    private String message;

    public LogInState(boolean success, String message) {
        this.success = success;
        this.message = message;
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
