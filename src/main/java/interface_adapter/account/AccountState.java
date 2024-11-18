package interface_adapter.account;

public class AccountState {

    public static AccountState DUMMY_STATE = new AccountState(false, null, null, null);

    private final boolean loggedIn;
    private final String username;
    private final String email;
    private final String password;

    public AccountState(boolean loggedIn, String username, String email, String password) {
        this.loggedIn = loggedIn;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

}
