package use_case.account;

import interface_adapter.account.AccountState;

public class AccountInputData {

    private final boolean loggedIn;
    private final String username;
    private final String email;
    private final String password;

    public AccountInputData(boolean loggedIn, String username, String email, String password) {
        this.loggedIn = loggedIn;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public AccountInputData(AccountState state) {
        this(state.isLoggedIn(), state.getUsername(), state.getEmail(), state.getPassword());
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


}
