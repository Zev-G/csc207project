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
            // create an user via data access
            dataAccess.createUser(inputData);

            // prepare success output
            outputBoundary.present(new SignUpOutputData(true, null));
        }
        catch (Exception e) {

            // prepare failure output
            outputBoundary.present(new SignUpOutputData(false, e.getMessage()));
        }
    }
}
