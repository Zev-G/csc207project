package data_access;

import com.google.firebase.database.DatabaseReference;
import com.google.api.core.ApiFuture;
import use_case.signup.SignUpInputData;

public class FirebaseSignUpDataAccess implements SignUpDataAccess {

    private final DatabaseReference database;

    public FirebaseSignUpDataAccess(DatabaseReference database) {
        this.database = database;
    }

    @Override
    public void createUser(SignUpInputData data) {
        String username = data.getUsername();
        ApiFuture<Void> future = database.child("users").child(username).setValueAsync(data);

        try {
            // Blocking call
            future.get();
            System.out.println("User created successfully!");
        }
        catch (Exception e) {
            System.err.println("Error creating user: " + e.getMessage());
        }
    }
}
