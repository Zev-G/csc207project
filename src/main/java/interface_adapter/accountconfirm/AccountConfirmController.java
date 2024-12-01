package interface_adapter.accountconfirm;

import interface_adapter.account.AccountState;
import use_case.account.AccountInputData;
import use_case.accountconfirm.AccountConfirmInputBoundary;

/**
 * Controls the "Confirm" button on the Account page
 */
public class AccountConfirmController {

    private AccountConfirmInputBoundary interactor;

    /**
     * Creates a new account confirm controller
     * @param interactor the input boundary for the controller
     */
    public AccountConfirmController(AccountConfirmInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Runs when the "Confirm" button is pressed
     * @param state
     */
    public void pressed(AccountState state) {
        interactor.pressed(new AccountInputData(state));
    }

}
