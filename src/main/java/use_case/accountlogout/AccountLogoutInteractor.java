package use_case.accountlogout;

public class AccountLogoutInteractor implements AccountLogoutInputBoundary {

    private final AccountLogoutOutputBoundary presenter;

    public AccountLogoutInteractor(AccountLogoutOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void logout() {
        // Does nothing intentionally
        presenter.loggedOut();
    }

}
