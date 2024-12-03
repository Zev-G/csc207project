package use_case.dataAccessInterface;

import entity.User;
import java.util.concurrent.CompletableFuture;

/**
 * Interface for accessing and managing user account data.
 */
public interface UserDataAccess {

    /**
     * Updates the username of a user.
     *
     * @param uid      The unique ID of the user.
     * @param username The new username.
     * @return {@code true} if the username was successfully changed, {@code false} otherwise.
     */
    boolean changeUsername(String uid, String username);

    /**
     * Updates the email of a user.
     *
     * @param uid   The unique ID of the user.
     * @param email The new email.
     * @return {@code true} if the email was successfully changed, {@code false} otherwise.
     */
    boolean changeEmail(String uid, String email);

    /**
     * Retrieves a user by their unique ID.
     *
     * @param uid The unique ID of the user.
     * @return A CompletableFuture that will complete with the user object associated with the ID.
     */
    CompletableFuture<User> getUser(String uid);

    /**
     * Deletes a user account.
     *
     * @param userId The unique ID of the user to be deleted.
     * @return {@code true} if the account was successfully deleted, {@code false} otherwise.
     */
    boolean deleteAccount(String userId);
}
