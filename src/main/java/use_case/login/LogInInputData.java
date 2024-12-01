package use_case.login;

/**
 * Represents the input data for the log-in use case.
 */
public class LogInInputData {
    private final String username;
    private final String email;
    private final String password;

    public LogInInputData(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
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
