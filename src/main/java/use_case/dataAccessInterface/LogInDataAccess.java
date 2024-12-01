package use_case.dataAccessInterface;

/**
 * Interface for handling log-in specific data access operations.
 */
public interface LogInDataAccess {

    /**
     * Finds a user by their credentials (username, email, and password).
     *
     * @param username The username of the user.
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return A user object if the credentials match, null otherwise.
     */
    User findUserByCredentials(String username, String email, String password);
}
