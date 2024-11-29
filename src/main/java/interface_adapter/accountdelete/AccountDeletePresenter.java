package interface_adapter.accountdelete;

import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import use_case.accountdelete.AccountDeleteOutputBoundary;
import view.pages.ViewManager;

/**
 * Presents the result of deleting the account.
 */
public class AccountDeletePresenter implements AccountDeleteOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final AccountViewModel viewModel;

    /**
     * Creates a new account delete presenter
     * @param viewManagerModel needed to go to a new page
     * @param viewModel needed to update the view model
     */
    public AccountDeletePresenter(ViewManagerModel viewManagerModel, AccountViewModel viewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewModel = viewModel;
    }


    @Override
    public void handleSuccess() {
        viewModel.setState(AccountState.DUMMY_STATE);
        viewManagerModel.setState("main");
    }

    @Override
    public void handleFail() {

    }
}
