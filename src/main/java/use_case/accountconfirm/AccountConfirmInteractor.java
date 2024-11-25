package use_case.accountconfirm;

import use_case.account.AccountInputData;

public class AccountConfirmInteractor implements AccountConfirmInputBoundary {

    private final AccountConfirmOutputBoundary presenter;

    public AccountConfirmInteractor(AccountConfirmOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void pressed(AccountInputData inputData) {
        // Do data base stuff
        presenter.handleSuccess();
    }

}
