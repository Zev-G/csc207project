package interface_adapter.account;

public class AccountState {

    public static AccountState DUMMY_STATE = new AccountState(false, null, null, null, 0);

    private final boolean loggedIn;
    private final String username;
    private final String email;
    private final String password;
    private final int userId;

    public AccountState(boolean loggedIn, String username, String email, String password, int userId) {
        this.loggedIn = loggedIn;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }
}
