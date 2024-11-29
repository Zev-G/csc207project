package interface_adapter.accountconfirm;

import interface_adapter.ViewManagerModel;
import interface_adapter.account.AccountViewModel;
import use_case.accountconfirm.AccountConfirmOutputBoundary;
import view.pages.ViewManager;

/**
 * Updates the UI after the "Confirm" button on the account page is pressed.
 */
public class AccountConfirmPresenter implements AccountConfirmOutputBoundary {

    private final ViewManagerModel viewManager;

    /**
         * Creates a new account confirm presenter
     * @param viewManager the view manager, needed to update the UI
     */
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
