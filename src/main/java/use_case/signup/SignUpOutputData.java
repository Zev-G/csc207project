package use_case.signup;

public class SignUpOutputData {

    private final boolean success;
    private final String errorMessage;

    public SignUpOutputData(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
