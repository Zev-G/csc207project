package interface_adapter.accountlogout;

import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import use_case.accountlogout.AccountLogoutOutputBoundary;

public class AccountLogoutPresenter implements AccountLogoutOutputBoundary {

    private final AccountViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public AccountLogoutPresenter(ViewManagerModel viewManagerModel, AccountViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void loggedOut() {
        viewModel.setState(AccountState.DUMMY_STATE);
        viewManagerModel.setState("main");
    }
}
