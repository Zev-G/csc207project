package use_case.login;

/**
 * Represents the output data for the log-in use case.
 */
public class LogInOutputData {
    private final boolean success;
    private final String message;

    public LogInOutputData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
