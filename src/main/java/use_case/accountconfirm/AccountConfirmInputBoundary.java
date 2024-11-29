package use_case.accountconfirm;

import use_case.account.AccountInputData;

/**
 * Defines the input boundary for confirming account actions.
 */
public interface AccountConfirmInputBoundary {

    /**
     * Handles the action triggered when the account confirmation button is pressed.
     *
     * @param inputData the account input data associated with the action
     */
    void pressed(AccountInputData inputData);
}
