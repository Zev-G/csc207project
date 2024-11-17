package interface_adapter.account;

public class AccountState {

    private final boolean loggedIn;

    public AccountState(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

}
