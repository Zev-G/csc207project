package view.pages;

import interface_adapter.account.AccountViewModel;

public class AccountPage extends Page {

    private final AccountViewModel viewModel;

    public AccountPage(AccountViewModel viewModel, PageManager pageManager) {
        super(pageManager);
        this.viewModel = viewModel;
    }
}
