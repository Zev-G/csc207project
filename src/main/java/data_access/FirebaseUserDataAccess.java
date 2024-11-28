package data_access;

import com.google.firebase.database.*;
import entity.CommonUser;
import java.util.concurrent.CompletableFuture;

public class FirebaseUserDataAccess {

    private final DatabaseReference database;

    public FirebaseUserDataAccess(DatabaseReference database) {
        this.database = database;
    }

    /**
     * Checks if a username is already taken in the Firebase Realtime Database.
     * @param username the username to check
     * @return CompletableFuture<Boolean> - true if available, false if taken
     */
    public CompletableFuture<Boolean> isUsernameAvailable(String username) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        database.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean available = true;

                // Iterate through all users to check if the username is taken
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String existingUsername = userSnapshot.child("name").getValue(String.class);
                    if (username.equals(existingUsername)) {
                        available = false;
                        break;
                    }
                }

                future.complete(available); // Complete with the result
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException()); // Complete exceptionally if there is an error
            }
        });

        return future;
    }

    /**
     * Creates a new user in the Firebase Realtime Database.
     * @param username the username of the new user
     * @param email the email of the new user
     * @param password the password of the new user
     * @return CompletableFuture<Void> - completed when the user is successfully created
     */
    public CompletableFuture<Void> createUser(String username, String email, String password) {
        CompletableFuture<Void> future = new CompletableFuture<>();

        String userId = database.push().getKey();
        if (userId == null) {
            future.completeExceptionally(new Exception("Failed to generate user ID"));
            return future;
        }

        CommonUser newUser = new CommonUser(username, password, email, Integer.parseInt(userId));

        database.child("users").child(userId).setValue(newUser, (error, ref) -> {
            if (error == null) {
                future.complete(null); // Successfully created user
            }
            else {
                future.completeExceptionally(error.toException()); // Handle database error
            }
        });

        return future;
    }

    /**
     * Retrieves a user's details by user ID.
     * @param userId the user ID
     * @return CompletableFuture<CommonUser> - the user details or null if not found
     */
    public CompletableFuture<CommonUser> getUserById(String userId) {
        CompletableFuture<CommonUser> future = new CompletableFuture<>();

        database.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CommonUser user = dataSnapshot.getValue(CommonUser.class);
                future.complete(user); // Complete with the user data
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException()); // Complete exceptionally on error
            }
        });

        return future;
    }
}
