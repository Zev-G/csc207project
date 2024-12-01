package use_case.signup;

import data_access.SignUpDataAccess;

public class SignUpInteractor implements SignUpInputBoundary {

    private final SignUpDataAccess dataAccess;
    private final SignUpOutputBoundary outputBoundary;

    public SignUpInteractor(SignUpDataAccess dataAccess, SignUpOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(SignUpInputData inputData) {
        try {

            validateInput(inputData);

            String userId = generateUniqueUserId();

            dataAccess.createUser(userId, inputData);

            outputBoundary.present(new SignUpOutputData(true, null));
        }
        catch (Exception e) {

            outputBoundary.present(new SignUpOutputData(false, e.getMessage()));
        }
    }

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

    private String generateUniqueUserId() {

        // generating a unique user ID
        return "user_" + System.currentTimeMillis();
    }
}
