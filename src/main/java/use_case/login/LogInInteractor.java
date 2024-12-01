package use_case.login;

import data_access.FirebaseLogInDataAccess;
import entity.User;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;

import java.util.concurrent.CompletableFuture;

/**
 * Handles the log-in use case logic.
 */
public class LogInInteractor implements LogInInputBoundary {

    private final FirebaseLogInDataAccess dataAccess;
    private final LogInOutputBoundary outputBoundary;
    private final AccountViewModel viewModel;

    public LogInInteractor(FirebaseLogInDataAccess dataAccess, LogInOutputBoundary outputBoundary, AccountViewModel viewModel) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
        this.viewModel = viewModel;
    }

    @Override
    public void logIn(LogInInputData inputData) {
        if (inputData.getUsername().isEmpty() || inputData.getEmail().isEmpty() || inputData.getPassword().isEmpty()) {
            outputBoundary.present(new LogInOutputData(false, "All fields are required."));
            return;
        }

        dataAccess.findUserByCredentials(inputData.getUsername(), inputData.getEmail(), inputData.getPassword())
                .thenAccept(user -> {
                    if (user != null) {
                        viewModel.setState(new AccountState(
                                true, user.getName(), user.getEmail(), user.getPassword(), user.getUserId()
                        ));
                        outputBoundary.present(new LogInOutputData(true, "Log-in successful!"));
                    }
                    else {
                        outputBoundary.present(new LogInOutputData(false, "Invalid credentials!"));
                    }
                })
                .exceptionally(ex -> {
                    outputBoundary.present(new LogInOutputData(false, "Error: " + ex.getMessage()));
                    return null;
                });
    }
}
