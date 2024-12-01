package use_case.signup;

/**
 * Data object containing the output of the Sign-Up process.
 * This class encapsulates whether the Sign-Up attempt was successful or not, and an optional error message if the attempt failed.
 */
public class SignUpOutputData {

    private final boolean success;
    private final String errorMessage;

    /**
     * Constructs a SignUpOutputData object.
     *
     * @param success      whether the Sign-Up attempt was successful
     * @param errorMessage the error message to display, or null if no error
     */
    public SignUpOutputData(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    /**
     * Returns whether the Sign-Up attempt was successful.
     *
     * @return true if successful, false otherwise
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Returns the error message, if any.
     *
     * @return the error message, or null if no error
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}