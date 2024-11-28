package interface_adapter.signup;

public class SignUpState {

    public static SignUpState DUMMY_STATE = new SignUpState(false, false, null);

    private final boolean isSigningUp;
    private final boolean signUpSuccess;
    private final String errorMessage;

}