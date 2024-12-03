package interface_adapter.signup;

/**
 * Represents the state of the Sign-Up page.
 * This class encapsulates the current state of the Sign Up process,
 * including whether the user is currently signing up and any error messages.
 */
public class SignUpState {

    private final boolean signingUp;
    private final String errorMessage;

    /**
     * Constructs a SignUpState object.
     *
     * @param signingUp   whether the user is currently signing up
     * @param errorMessage the error message to display, or null if no error
     */
    public SignUpState(boolean signingUp, String errorMessage) {
        this.signingUp = signingUp;
        this.errorMessage = errorMessage;
    }

    /**
     * Returns whether the user is currently signing up.
     *
     * @return true if the user is signing up, false otherwise
     */
    public boolean isSigningUp() {
        return signingUp;
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