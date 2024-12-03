package interface_adapter.signup;

import use_case.signup.SignUpOutputBoundary;
import use_case.signup.SignUpOutputData;

/**
 * Presenter for the Sign-Up use case.
 * This class receives output data from the use case interactor and updates the `SignUpViewModel` to reflect the result of the Sign-Up process.
 */
public class SignUpPresenter implements SignUpOutputBoundary {

    private final SignUpViewModel viewModel;

    /**
     * Constructs a SignUpPresenter.
     *
     * @param viewModel the ViewModel to update with the output data
     */
    public SignUpPresenter(SignUpViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Presents the output data to the user interface.
     * This method updates the `SignUpViewModel`'s state based on the `SignUpOutputData` received from the use case interactor.
     *
     * @param outputData the output data containing success or error information
     */
    @Override
    public void present(SignUpOutputData outputData) {
        SignUpState state = new SignUpState(outputData.isSuccess(), outputData.getErrorMessage());
        viewModel.setState(state);
    }
}