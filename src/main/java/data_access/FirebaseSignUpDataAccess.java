package data_access;

import com.google.firebase.database.DatabaseReference;
import com.google.api.core.ApiFuture;
import use_case.signup.SignUpInputData;

import java.util.Map;

public class FirebaseSignUpDataAccess implements SignUpDataAccess {

    private final DatabaseReference database;

    public FirebaseSignUpDataAccess(DatabaseReference database) {
        this.database = database;
    }

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
