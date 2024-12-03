package use_case.signup;

/**
 * Input Boundary for the Sign-Up use case.
 * This interface defines the contract for any class that wants to initiate the Sign Up process.
 * It provides a single method `execute` to trigger the Sign Up logic.
 */
public interface SignUpInputBoundary {
    void execute(SignUpInputData inputData);
}