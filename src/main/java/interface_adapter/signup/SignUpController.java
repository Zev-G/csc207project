package interface_adapter.signup;

import use_case.signup.SignUpInputBoundary;
import use_case.signup.SignUpInputData;

public class SignUpController {

    private final SignUpInputBoundary interactor;

    public SignUpController(SignUpInputBoundary interactor) {
        this.interactor = interactor;
    }
}
