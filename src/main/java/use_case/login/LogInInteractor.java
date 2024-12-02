package use_case.login;

import data_access.FirebaseLogInDataAccess;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;

/**
 * Handles the log-in use case logic.
 * Validates input data, interacts with the data access layer, and updates the output boundary and account state.
 */
public class LogInInteractor implements LogInInputBoundary {

    private final FirebaseLogInDataAccess dataAccess;
    private final LogInOutputBoundary outputBoundary;
    private final AccountViewModel viewModel;

    /**
     * Constructs a LogInInteractor with the specified dependencies.
     *
     * @param dataAccess     The data access object for interacting with user data.
     * @param outputBoundary The output boundary for presenting log-in results.
     * @param viewModel      The account view model for updating account state.
     */
    public LogInInteractor(FirebaseLogInDataAccess dataAccess, LogInOutputBoundary outputBoundary, AccountViewModel viewModel) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
        this.viewModel = viewModel;
    }

    /**
     * Processes the log-in request by validating input data and fetching user information.
     * Updates the output boundary and account state based on the result.
     *
     * @param inputData The input data containing user credentials.
     */
    @Override
    public void logIn(LogInInputData inputData) {
        if (inputData.getUsername().isEmpty() || inputData.getEmail().isEmpty() || inputData.getPassword().isEmpty()) {
            outputBoundary.present(new LogInOutputData(false, "All fields are required."));
            return;
        }

        dataAccess.findUserByCredentials(inputData.getUsername(), inputData.getEmail(), inputData.getPassword())
                .thenAccept(user -> {
                    if (user != null) {
                        // Set AccountState with user details
                        AccountState accountState = new AccountState(
                                true,
                                user.getName(),
                                user.getEmail(),
                                user.getPassword(),
                                user.getUserId()
                        );
                        viewModel.setState(accountState);
                        System.out.println("AccountState set: " + viewModel.getState());

                        // Notify output boundary of success
                        outputBoundary.present(new LogInOutputData(true, "Log-in successful!"));
                        outputBoundary.onLoginSuccess();
                    } else {
                        outputBoundary.present(new LogInOutputData(false, "Invalid credentials!"));
                    }
                })
                .exceptionally(ex -> {
                    outputBoundary.present(new LogInOutputData(false, "Error: " + ex.getMessage()));
                    return null;
                });
    }
}
