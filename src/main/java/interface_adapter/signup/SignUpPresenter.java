package interface_adapter.signup;

import use_case.signup.SignUpOutputBoundary;
import use_case.signup.SignUpOutputData;

public class SignUpPresenter implements SignUpOutputBoundary {

    private final SignUpViewModel viewModel;

    public SignUpPresenter(SignUpViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
