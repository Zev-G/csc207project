package interface_adapter.accountconfirm;

import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountViewModel;
import use_case.accountconfirm.AccountConfirmOutputBoundary;
import view.pages.ViewManager;

public class AccountConfirmPresenter implements AccountConfirmOutputBoundary {

    private final ViewManagerModel viewManager;

    public AccountConfirmPresenter(ViewManagerModel viewManager) {
        this.viewManager = viewManager;
    }

    @Override
    public void handleSuccess() {
        viewManager.setState("main");
    }

    @Override
    public void handleFail() {

    }
}
