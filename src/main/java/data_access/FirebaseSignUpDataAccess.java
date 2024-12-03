package data_access;

import com.google.firebase.database.DatabaseReference;
import com.google.api.core.ApiFuture;
import use_case.dataAccessInterface.SignUpDataAccess;
import use_case.signup.SignUpInputData;

import java.util.Map;

/**
 * Implementation of SignUpDataAccess using Firebase Realtime Database.
 * This class handles creating new user accounts in Firebase.
 */
public class FirebaseSignUpDataAccess implements SignUpDataAccess {

    private final DatabaseReference database;

    /**
     * Constructor for FirebaseSignUpDataAccess.
     *
     * @param database a reference to the Firebase Realtime Database
     */
    public FirebaseSignUpDataAccess(DatabaseReference database) {
        this.database = database;
    }

    /**
     * Creates a new user account in Firebase Realtime Database.
     *
     * @param userId the unique identifier for the new user
     * @param data   the data required to create the user account, including username, password, and email.
     */
    @Override
    public void createUser(String userId, SignUpInputData data) {

        // Prepare user data for Firebase
        Map<String, Object> userData = Map.of(
                "username", data.getUsername(),
                "email", data.getEmail(),
                "password", data.getPassword(),
                "correctGuesses", 0,
                "gamesPlayed", 0,
                "points", 0
        );

        // Write user data to Firebase under the unique user ID
        DatabaseReference newUserRef = database.child("users").child(userId);
        ApiFuture<Void> future = newUserRef.setValueAsync(userData);

        try {
            future.get();
            System.out.println("User created successfully!");
        }
        catch (Exception e) {
            System.err.println("Error creating user: " + e.getMessage());
        }
    }
}