package interface_adapter.accountlogout;

import use_case.accountlogout.AccountLogoutInputBoundary;

/**
 * Controller for handling account logout operations.
 * This class acts as an intermediary between the view layer and the use case layer
 * for the logout process.
 */
public class AccountLogoutController {

    private final AccountLogoutInputBoundary inputBoundary;

    /**
     * Constructs an AccountLogoutController with the specified input boundary.
     *
     * @param inputBoundary the input boundary responsible for handling the logout logic
     */
    public AccountLogoutController(AccountLogoutInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Initiates the logout process by delegating to the input boundary.
     */
    public void logout() {
        inputBoundary.logout();
    }
}
