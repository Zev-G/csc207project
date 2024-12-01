package entity;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Returns the email of the user.
     * @return the email of the user.
     */
    String getEmail();

    /**
     * Returns the user id.
     * @return the user id.
     */
    String getUserId();

}
