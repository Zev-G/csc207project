package use_case.signup;

/**
 * Output Boundary for the Sign-Up use case.
 * This interface defines the contract for any class that wants to receive the output of the Sign Up process.
 * It provides a single method `present` to display the result of the Sign Up attempt to the user.
 */
public interface SignUpOutputBoundary {
    void present(SignUpOutputData outputData);
}