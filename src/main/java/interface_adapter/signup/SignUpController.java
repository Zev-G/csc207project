package interface_adapter.signup;

import use_case.signup.SignUpInputBoundary;
import use_case.signup.SignUpInputData;

/**
 * Controller class for handling the Sign-Up process.
 * This class acts as a bridge between the user interface and the Sign Up use case.
 * It receives user input, creates a `SignUpInputData` object, and passes it to the interactor.
 */
public class SignUpController {

    private final SignUpInputBoundary interactor;

    /**
     * Constructs a SignUpController.
     *
     * @param interactor the interactor responsible for handling the Sign Up use case
     */
    public SignUpController(SignUpInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Handles the Sign-Up process.
     * This method receives user input for username, email, and password, creates a `SignUpInputData` object,
     * and passes it to the `SignUpInputBoundary` interactor for further processing.
     *
     * @param username the desired username
     * @param email    the user's email address
     * @param password the desired password
     */
    public void handleSignUp(String username, String email, String password) {

        SignUpInputData inputData = new SignUpInputData(username, email, password);

        interactor.execute(inputData);
    }
}