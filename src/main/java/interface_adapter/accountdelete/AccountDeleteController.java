package interface_adapter.accountdelete;

import interface_adapter.account.AccountState;
import use_case.account.AccountInputData;
import use_case.accountdelete.AccountDeleteInputBoundary;

public class AccountDeleteController {

    private final AccountDeleteInputBoundary interactor;

    public AccountDeleteController(AccountDeleteInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void pressed(AccountState state) {
        interactor.deleteAccount(new AccountInputData(state));
    }

}
