package interface_adapter.signup;

import interface_adapter.ViewModel;

/**
 * ViewModel for the Sign-Up page extending ViewModel.
 * This class manages the state of the Sign Up page and notifies listeners of changes.
 */
public class SignUpViewModel extends ViewModel<SignUpState> {

    /**
     * Constructs a SignUpViewModel with the default view name "signUpPage".
     * Sets the initial state.
     */
    public SignUpViewModel() {
        super("signUpPage");
        setState(new SignUpState(false, null));
    }
}