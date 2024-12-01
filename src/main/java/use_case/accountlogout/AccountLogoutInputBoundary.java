package use_case.accountlogout;

import use_case.account.AccountInputData;

/**
 * Defines the input boundary for logging out of an account.
 */
public interface AccountLogoutInputBoundary {

    /**
     * Logs out the current user.
     */
    void logout();
}
