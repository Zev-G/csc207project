package interface_adapter.accountconfirm;

import interface_adapter.account.AccountState;
import use_case.account.AccountInputData;
import use_case.accountconfirm.AccountConfirmInputBoundary;

public class AccountConfirmController {

    private AccountConfirmInputBoundary interactor;

    public AccountConfirmController(AccountConfirmInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void pressed(AccountState state) {
        interactor.pressed(new AccountInputData(state));
    }

}
