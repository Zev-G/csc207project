package data_access;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.api.core.ApiFuture;
import entity.User;

public class UserDataAccess {
    private final DatabaseReference database;

    public UserDataAccess() {
        this.database = FirebaseDatabase.getInstance().getReference("users");
    }

    public void addUser(User user, String email, String password) {
        try {
            // Step 1: Create a Firebase Authentication User
            CreateRequest authRequest = new CreateRequest()
                    .setEmail(email)
                    .setPassword(password)
                    .setEmailVerified(false);

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(authRequest);

            // Step 2: Use the Authentication UID as the database key
            String firebaseUid = userRecord.getUid();

            // Step 4: Save User Details to the Database
            DatabaseReference userRef = database.child(firebaseUid); // Use UID as the database key
            ApiFuture<Void> future = userRef.setValueAsync(user);

            // Block and wait for the operation to complete
            future.get(); // Wait for the operation to complete successfully
            System.out.println("User added successfully with Firebase UID: " + firebaseUid);
        } catch (Exception e) {
            System.err.println("Error creating user: " + e.getMessage());
        }
    }
}
