package interface_adapter.accountdelete;

import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import use_case.accountdelete.AccountDeleteOutputBoundary;
import view.pages.ViewManager;

public class AccountDeletePresenter implements AccountDeleteOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final AccountViewModel viewModel;

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
