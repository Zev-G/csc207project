//package interface_adapter.signup;
//
///**
// * Represents the state of the SignUp process.
// */
//public class SignUpState {
//
//    private final boolean signingUp; // Indicates if a sign-up is in progress
//    private final String username;
//    private final String email;
//    private final String password;
//    private final String errorMessage;
//
//    public SignUpState(boolean signingUp, String username, String email, String password, String errorMessage) {
//
//        this.signingUp = signingUp;
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.errorMessage = errorMessage;
//
//    }
//
//    public boolean isSigningUp() {
//
//        return signingUp;
//
//    }
//
//    public String getUsername() {
//
//        return username;
//
//    }
//
//    public String getEmail() {
//
//        return email;
//
//    }
//
//    public String getPassword() {
//
//        return password;
//
//    }
//
//    public String getErrorMessage() {
//
//        return errorMessage;
//
//    }
//
//    public static SignUpState initial() {
//
//        return new SignUpState(false, "", "", "", null);
//
//    }
//
//    public SignUpState withError(String errorMessage) {
//
//        return new SignUpState(this.signingUp, this.username, this.email, this.password, errorMessage);
//
//    }
//
//    public SignUpState asSigningUp() {
//
//        return new SignUpState(true, this.username, this.email, this.password, null);
//
//    }
//
//    public SignUpState withCredentials(String username, String email, String password) {
//
//        return new SignUpState(this.signingUp, username, email, password, this.errorMessage);
//
//    }
//}

package interface_adapter.signup;

public enum SignUpState {

    private final boolean signingUp;
    private final String errorMessage;

    public SignUpState(boolean signingUp, String errorMessage) {
        this.signingUp = signingUp;
        this.errorMessage = errorMessage;
    }

    public boolean isSigningUp() {
        return signingUp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}