package use_case.signup;

import use_case.dataAccessInterface.SignUpDataAccess;

/**
 * Interactor for the Sign-Up use case.
 * This class coordinates the Sign-Up process, including validating user input, creating a new user account, and handling potential errors.
 */
public class  SignUpInteractor implements SignUpInputBoundary {

    private final SignUpDataAccess dataAccess;
    private final SignUpOutputBoundary outputBoundary;

    /**
     * Constructs a SignUpInteractor.
     *
     * @param dataAccess     the data access object for creating user accounts
     * @param outputBoundary the output boundary for presenting the result to the user
     */
    public SignUpInteractor(SignUpDataAccess dataAccess, SignUpOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Executes the Sign-Up process.
     * Validates user input, creates a new user account, and handles the result of the operation.
     * If validation or account creation fails, an appropriate error is sent to the output boundary.
     *
     * @param inputData The user input data containing the desired username, email, and password.
     * @throws IllegalArgumentException if the input data is invalid (e.g., empty fields).
     */
    @Override
    public void execute(SignUpInputData inputData) {
        try {
            validateInput(inputData);
            String userId = generateUniqueUserId();
            dataAccess.createUser(userId, inputData);
            outputBoundary.present(new SignUpOutputData(true, null));
        } catch (Exception e) {
            outputBoundary.present(new SignUpOutputData(false, e.getMessage()));
        }
    }


    /**
     * Validates the user input data.
     * This method checks if the username, email, and password are not empty.
     * If any of the fields are empty, an `IllegalArgumentException` is thrown.
     *
     * @param inputData the user input data to validate
     * @throws IllegalArgumentException if the input data is invalid
     */
    private void validateInput(SignUpInputData inputData) throws IllegalArgumentException {
        if (inputData.getUsername() == null || inputData.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (inputData.getEmail() == null || inputData.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        if (inputData.getPassword() == null || inputData.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
    }

    /**
     * Generates a unique user ID.
     * This method generates a unique user ID by combining the prefix "user_" with the current timestamp.
     *
     * @return the generated unique user ID
     */
    private String generateUniqueUserId() {

        // generating a unique user ID
        return "user_" + System.currentTimeMillis();
    }
}