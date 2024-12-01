package use_case.signup;

/**
 * Data object containing user input for the Sign-Up process.
 * This class encapsulates the user's desired username, email address, and password.
 */
public class SignUpInputData {

    private final String username;
    private final String email;
    private final String password;

    /**
     * Constructs a SignUpInputData object.
     *
     * @param username the desired username
     * @param email    the user's email address
     * @param password the desired password
     */
    public SignUpInputData(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the user's desired username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the user's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the user's desired password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}