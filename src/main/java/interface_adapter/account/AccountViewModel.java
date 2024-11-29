package interface_adapter.account;

import interface_adapter.ViewModel;

/**
 * The view model for the account state
 */
public class AccountViewModel extends ViewModel<AccountState> {

    /**
     * Creates a new account view model
     */
    public AccountViewModel() {
        super("account");
    }

}
