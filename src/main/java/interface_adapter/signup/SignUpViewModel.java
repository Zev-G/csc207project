package interface_adapter.signup;

import data_access.FirebaseUserDataAccess;
import interface_adapter.ViewModel;

public class SignUpViewModel extends ViewModel<SignUpState> {

    private final FirebaseUserDataAccess userDataAccess;

}
