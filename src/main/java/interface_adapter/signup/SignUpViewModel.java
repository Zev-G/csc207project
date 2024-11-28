package interface_adapter.signup;

import interface_adapter.ViewModel;
import data_access.FirebaseUserDataAccess;
import java.util.concurrent.CompletableFuture;

/**
 * ViewModel for handling sign-up logic.
 */
public class SignUpViewModel extends ViewModel<SignUpState> {
    private final FirebaseUserDataAccess userDataAccess;

    public SignUpViewModel(FirebaseUserDataAccess userDataAccess) {
        super("signup");
        this.userDataAccess = userDataAccess;
        setState(SignUpState.initial());
    }
}
