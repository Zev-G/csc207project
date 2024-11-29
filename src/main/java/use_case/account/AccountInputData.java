package use_case.account;

import interface_adapter.account.AccountState;

/**
 * Represents input data for an account, including login status and user details.
 */
public class AccountInputData {

    private final boolean loggedIn;
    private final String username;
    private final String email;
    private final String password;
    private final int userId;

    /**
     * Constructs an AccountInputData object with specified account details.
     *
     * @param loggedIn whether the user is logged in
     * @param username the username of the account
     * @param email    the email associated with the account
     * @param password the password for the account
     * @param userId   the unique ID of the user
     */
    public AccountInputData(boolean loggedIn, String username, String email, String password, int userId) {
        this.loggedIn = loggedIn;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userId = userId;
    }

    /**
     * Constructs an AccountInputData object from an AccountState object.
     *
     * @param state the AccountState object containing account details
     */
    public AccountInputData(AccountState state) {
        this(state.isLoggedIn(), state.getUsername(), state.getEmail(), state.getPassword(), state.getUserId());
    }

    /**
     * Returns whether the user is logged in.
     *
     * @return {@code true} if the user is logged in, otherwise {@code false}
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Returns the username of the account.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the email associated with the account.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the password for the account.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the unique ID of the user.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }
}
