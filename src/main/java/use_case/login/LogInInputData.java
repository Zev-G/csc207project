package use_case.login;

/**
 * Represents the input data required for the log-in process.
 * Contains user credentials such as username, email, and password.
 */
public class LogInInputData {
    private final String username;
    private final String email;
    private final String password;

    /**
     * Constructs a LogInInputData object with the specified user credentials.
     *
     * @param username The username entered by the user.
     * @param email    The email entered by the user.
     * @param password The password entered by the user.
     */
    public LogInInputData(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the username entered by the user.
     *
     * @return The username as a string.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the email entered by the user.
     *
     * @return The email as a string.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the password entered by the user.
     *
     * @return The password as a string.
     */
    public String getPassword() {
        return password;
    }
}
