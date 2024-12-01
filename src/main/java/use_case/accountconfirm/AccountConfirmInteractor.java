package use_case.accountconfirm;

import use_case.dataAccessInterface.UserDataAccess;
import use_case.account.AccountInputData;

/**
 * Interactor for handling account confirmation actions.
 * It processes user input and interacts with the data access layer,
 * delegating success or failure handling to the presenter.
 */
public class AccountConfirmInteractor implements AccountConfirmInputBoundary {

    private final AccountConfirmOutputBoundary presenter;
    private final UserDataAccess userDataAccess;

    /**
     * Constructs an AccountConfirmInteractor.
     *
     * @param userDataAccess the data access interface for user account operations
     * @param presenter      the output boundary to handle success or failure responses
     */
    public AccountConfirmInteractor(UserDataAccess userDataAccess, AccountConfirmOutputBoundary presenter) {
        this.presenter = presenter;
        this.userDataAccess = userDataAccess;
    }

    /**
     * Handles the account confirmation action triggered by the user.
     * Updates the user's email and username in the database and
     * notifies the presenter of success or failure.
     *
     * @param inputData the input data containing user account details
     */
    @Override
    public void pressed(AccountInputData inputData) {
        boolean success1 = userDataAccess.changeEmail(inputData.getUserId(), inputData.getEmail());
        boolean success2 = userDataAccess.changeUsername(inputData.getUserId(), inputData.getUsername());

        if (!success1 || !success2) {
            presenter.handleFail();
            return;
        }
        presenter.handleSuccess();
    }
}
