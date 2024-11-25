package use_case.accountlogout;

import data_access.UserDataAccess;
import use_case.account.AccountInputData;
import use_case.accountconfirm.AccountConfirmOutputBoundary;

public class AccountLogoutInteractor implements AccountLogoutInputBoundary {

    private final AccountLogoutOutputBoundary presenter;

    public AccountLogoutInteractor(AccountLogoutOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void logout() {
        // Does nothing intentionally
        presenter.loggedOut();
    }

}
