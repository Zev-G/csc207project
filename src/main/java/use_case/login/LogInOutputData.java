package use_case.login;

/**
 * Represents the output data of the log-in process.
 * Contains information on whether the process was successful and any associated messages.
 */
public class LogInOutputData {
    private final boolean success;
    private final String message;

    /**
     * Constructs a LogInOutputData object with the specified success status and message.
     *
     * @param success Indicates whether the log-in was successful.
     * @param message The message associated with the log-in result.
     */
    public LogInOutputData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Returns whether the log-in was successful.
     *
     * @return {@code true} if the log-in was successful, {@code false} otherwise.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Returns the message associated with the log-in result.
     *
     * @return The log-in result message.
     */
    public String getMessage() {
        return message;
    }
}
