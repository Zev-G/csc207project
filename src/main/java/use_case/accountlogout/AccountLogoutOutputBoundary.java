package use_case.accountlogout;

/**
 * Defines the output boundary for handling the result of a logout action.
 */
public interface AccountLogoutOutputBoundary {

    /**
     * Handles the successful logout of a user.
     */
    void loggedOut();
}
