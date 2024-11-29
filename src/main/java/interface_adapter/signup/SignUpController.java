package interface_adapter.signup;

import use_case.signup.SignUpInputBoundary;
import use_case.signup.SignUpInputData;

public class SignUpController {

    private final SignUpInputBoundary interactor;

    public SignUpController(SignUpInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void handleSignUp(String username, String email, String password) {
        SignUpInputData inputData = new SignUpInputData(username, email, password);
        interactor.execute(inputData);
    }
}
