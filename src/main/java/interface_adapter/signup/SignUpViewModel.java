package interface_adapter.signup;

import interface_adapter.ViewModel;
import data_access.FirebaseUserDataAccess;
import java.util.concurrent.CompletableFuture;

/**
 * ViewModel for handling sign-up logic.
 */
public class SignUpViewModel extends ViewModel<SignUpState> {
    private final FirebaseUserDataAccess userDataAccess;

    public SignUpViewModel(FirebaseUserDataAccess userDataAccess) {
        super("signup");
        this.userDataAccess = userDataAccess;
        setState(SignUpState.initial());
    }

    /**
     * Sets the credentials in the state.
     * @param username the username
     * @param email the email
     * @param password the password
     */
    public void setCredentials(String username, String email, String password) {

        setState(getState().withCredentials(username, email, password));

    }
    /**
     * Initiates the sign-up process.
     */
    public void signUp() {
        SignUpState currentState = getState();

        // Ensure the state has valid credentials
        if (currentState.getUsername().isEmpty() || currentState.getEmail().isEmpty() || currentState.getPassword().isEmpty()) {
            setState(currentState.withError("All fields are required."));
        }

        // Update state to show that sign-up is in progress
        setState(currentState.asSigningUp());

        // Interact with FirebaseUserDataAccess
        userDataAccess.createUser(
                currentState.getUsername(),
                currentState.getEmail(),
                currentState.getPassword()
        ).thenAccept(result -> {
            // Success: Reset the state
            setState(SignUpState.initial());
        }).exceptionally(ex -> {
            // Failure: Update state with an error message
            setState(currentState.withError(ex.getMessage()));
            return null;
        });
    }
}
