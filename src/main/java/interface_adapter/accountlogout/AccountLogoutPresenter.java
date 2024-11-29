package interface_adapter.accountlogout;

import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import use_case.accountlogout.AccountLogoutOutputBoundary;


/**
 * Presenter class for handling account logout operations.
 * This class implements the AccountLogoutOutputBoundary interface.
 */
public class AccountLogoutPresenter implements AccountLogoutOutputBoundary {

    private final AccountViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a new AccountLogoutPresenter.
     *
     * @param viewManagerModel The ViewManagerModel to manage view states.
     * @param viewModel The AccountViewModel to update account-related view states.
     */
    public AccountLogoutPresenter(ViewManagerModel viewManagerModel, AccountViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Handles the completion of the logout process.
     * Sets the account state to a dummy state and updates the view to the main screen.
     */
    @Override
    public void loggedOut() {
        viewModel.setState(AccountState.DUMMY_STATE);
        viewManagerModel.setState("main");
    }
}
