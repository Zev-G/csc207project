package interface_adapter.account;

/**
 * Represents the current state of the logged into account, as well as whether or not the user is logged in.
 */
public class AccountState {

    // TODO rename this
    public static AccountState DUMMY_STATE = new AccountState(false, null, null, null, 0);

    private final boolean loggedIn;
    private final String username;
    private final String email;
    private final String password;
    private final int userId;

    /**
     * Creates a new AccountState with the given inputs
     * @param loggedIn whether the user is logged in
     * @param username the user's username
     * @param email the user's email
     * @param password the user's email
     * @param userId the id of the user
     */
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
