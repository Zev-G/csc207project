package use_case.signup;

import data_access.SignUpDataAccess;

public class SignUpInteractor implements SignUpDataAccess {

    private final SignUpDataAccess dataAccess;
    private final SignUpOutputBoundary outputBoundary;

    public SignUpInteractor(SignUpDataAccess dataAccess, SignUpOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }
}
