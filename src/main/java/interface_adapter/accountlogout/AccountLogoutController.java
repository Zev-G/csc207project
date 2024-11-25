package interface_adapter.accountlogout;

import interface_adapter.account.AccountState;
import use_case.accountlogout.AccountLogoutInputBoundary;

public class AccountLogoutController {

    private final AccountLogoutInputBoundary inputBoundary;

    public AccountLogoutController(AccountLogoutInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void logout() {
        inputBoundary.logout();
    }

}
