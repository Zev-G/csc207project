package interface_adapter.login;

/**
 * Represents the state of the log-in process.
 * Contains fields for indicating whether logging in is in progress,
 * whether it was successful, and any associated messages.
 */
public class LogInState {
    private boolean isLoggingIn;
    private boolean success;
    private String message;

    /**
     * Constructs a LogInState with the specified properties.
     *
     * @param isLoggingIn Indicates whether the log-in process is in progress.
     * @param success     Indicates whether the log-in was successful.
     * @param message     The message associated with the log-in state.
     */
    public LogInState(boolean isLoggingIn, boolean success, String message) {
        this.isLoggingIn = isLoggingIn;
        this.success = success;
        this.message = message;
    }

    /**
     * Returns whether the log-in process is in progress.
     *
     * @return {@code true} if logging in is in progress, {@code false} otherwise.
     */
    public boolean isLoggingIn() {
        return isLoggingIn;
    }

    /**
     * Sets whether the log-in process is in progress.
     *
     * @param isLoggingIn {@code true} if logging in is in progress, {@code false} otherwise.
     */
    public void setLoggingIn(boolean isLoggingIn) {
        this.isLoggingIn = isLoggingIn;
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
     * Sets whether the log-in was successful.
     *
     * @param success {@code true} if the log-in was successful, {@code false} otherwise.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Returns the message associated with the log-in state.
     *
     * @return The log-in message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message associated with the log-in state.
     *
     * @param message The log-in message.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
