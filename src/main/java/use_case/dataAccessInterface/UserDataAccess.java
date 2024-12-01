package use_case.dataAccessInterface;

import entity.CommonUser;
import entity.User;

/**
 * Interface for accessing and managing user account data.
 */
public interface UserDataAccess {

    /**
     * Updates the username of a user.
     *
     * @param uid      the unique ID of the user
     * @param username the new username
     * @return {@code true} if the username was successfully changed, {@code false} otherwise
     */
    boolean changeUsername(int uid, String username);

    /**
     * Updates the email of a user.
     *
     * @param uid   the unique ID of the user
     * @param email the new email
     * @return {@code true} if the email was successfully changed, {@code false} otherwise
     */
    boolean changeEmail(int uid, String email);

    /**
     * Retrieves a user by their unique ID.
     *
     * @param uid the unique ID of the user
     * @return the user object associated with the ID
     */
    User getUser(int uid);

    /**
     * Deletes a user account.
     *
     * @param userId the unique ID of the user to be deleted
     * @return {@code true} if the account was successfully deleted, {@code false} otherwise
     */
    boolean deleteAccount(int userId);
}
