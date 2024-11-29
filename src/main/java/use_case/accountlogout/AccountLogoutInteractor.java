package use_case.accountlogout;

/**
 * Interactor for handling account logout actions.
 * Delegates the logout result to the output boundary.
 */
public class AccountLogoutInteractor implements AccountLogoutInputBoundary {

    private final AccountLogoutOutputBoundary presenter;

    /**
     * Constructs an AccountLogoutInteractor.
     *
     * @param presenter the output boundary to handle the logout result
     */
    public AccountLogoutInteractor(AccountLogoutOutputBoundary presenter) {
        this.presenter = presenter;
    }

    /**
     * Handles the logout action.
     * Currently, it performs no additional logic and notifies the presenter.
     */
    @Override
    public void logout() {
        presenter.loggedOut();
    }
}
