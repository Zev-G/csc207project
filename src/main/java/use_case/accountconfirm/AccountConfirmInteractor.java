package use_case.accountconfirm;

import data_access.UserDataAccess;
import use_case.account.AccountInputData;

public class AccountConfirmInteractor implements AccountConfirmInputBoundary {

    private final AccountConfirmOutputBoundary presenter;
    private final UserDataAccess userDataAccess;

    public AccountConfirmInteractor(UserDataAccess userDataAccess, AccountConfirmOutputBoundary presenter) {
        this.presenter = presenter;
        this.userDataAccess = userDataAccess;
    }

    @Override
    public void pressed(AccountInputData inputData) {
        // Do data base stuff
        boolean success1 = userDataAccess.changeEmail(inputData.getUserId(), inputData.getEmail());
        boolean success2 = userDataAccess.changeUsername(inputData.getUserId(), inputData.getUsername());
        if (!success1 || !success2) {
            presenter.handleFail();
            return;
        }
        presenter.handleSuccess();
    }

}
