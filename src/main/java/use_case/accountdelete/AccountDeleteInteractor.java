package use_case.accountdelete;

import use_case.dataAccessInterface.UserDataAccess;
import use_case.account.AccountInputData;

/**
 * Interactor for handling account deletion actions.
 * Processes the account deletion request and delegates success or failure handling to the output boundary.
 */
public class AccountDeleteInteractor implements AccountDeleteInputBoundary {

    private final AccountDeleteOutputBoundary outputBoundary;
    private final UserDataAccess dataAccess;

    /**
     * Constructs an AccountDeleteInteractor.
     *
     * @param outputBoundary the output boundary to handle the result of the deletion
     * @param dataAccess     the data access interface for user account operations
     */
    public AccountDeleteInteractor(AccountDeleteOutputBoundary outputBoundary, UserDataAccess dataAccessMock) {
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccessMock;
    }

    /**
     * Deletes a user account based on the provided input data.
     * Notifies the output boundary of the result.
     *
     * @param inputData the input data containing the user ID for account deletion
     */
    @Override
    public void deleteAccount(AccountInputData inputData) {
        boolean success = dataAccess.deleteAccount(inputData.getUserId());
        if (success) {
            outputBoundary.handleSuccess();
        } else {
            outputBoundary.handleFail();
        }
    }
}
