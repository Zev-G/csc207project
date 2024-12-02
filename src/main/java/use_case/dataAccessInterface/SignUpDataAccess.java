package use_case.dataAccessInterface;

import use_case.signup.SignUpInputData;

/**
 * Interface for accessing and managing sign up data.
 */
public interface SignUpDataAccess {

    /**
     * Creates a new user account.
     *
     * @param userId the unique identifier for the new user
     * @param data   the data required to create the user account, including username, password, and email.
     */
    void createUser(String userId, SignUpInputData data);
}