package interface_adapter.accountdelete;

import interface_adapter.account.AccountState;
import use_case.account.AccountInputData;
import use_case.accountdelete.AccountDeleteInputBoundary;

/**
 * Handles the "Delete" button on the account page
 */
public class AccountDeleteController {

    private final AccountDeleteInputBoundary interactor;

    /**
     * Creates a new account delete controller
     * @param interactor the input boundary
     */
    public AccountDeleteController(AccountDeleteInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Runs when the delete button is pressed
     * @param state the current user's account info
     */
    public void pressed(AccountState state) {
        interactor.deleteAccount(new AccountInputData(state));
    }

}
