package use_case.accountdelete;

import use_case.account.AccountInputData;

/**
 * Defines the input boundary for deleting an account.
 */
public interface AccountDeleteInputBoundary {

    /**
     * Deletes an account based on the provided account input data.
     *
     * @param inputData the account input data containing details for the deletion
     */
    void deleteAccount(AccountInputData inputData);
}
