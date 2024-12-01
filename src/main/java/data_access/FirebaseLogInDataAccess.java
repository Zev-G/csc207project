package data_access;

import com.google.firebase.database.*;
import entity.CommonUser;
import entity.User;
import use_case.dataAccessInterface.LogInDataAccess;
import use_case.dataAccessInterface.UserDataAccess;

import java.util.concurrent.CompletableFuture;

/**
 * Firebase implementation for managing user data and log-in functionality.
 */
public class FirebaseLogInDataAccess implements LogInDataAccess, UserDataAccess {
    private final DatabaseReference usersRef;

    /**
     * Constructor initializes the Firebase users reference.
     *
     * @param databaseReference Root database reference.
     */
    public FirebaseLogInDataAccess(DatabaseReference databaseReference) {
        this.usersRef = databaseReference.child("users");
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param uid The unique ID of the user (as a string).
     * @return A CompletableFuture that will complete with the user object if found, null otherwise.
     */
    @Override
    public CompletableFuture<User> getUser(String uid) {
        CompletableFuture<User> future = new CompletableFuture<>();

        usersRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Complete with user data
                    future.complete(mapSnapshotToUser(dataSnapshot));
                }
                else {
                    // Complete with null if user is not found
                    future.complete(null);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(new Exception("Error fetching user: " + databaseError.getMessage()));
            }
        });

        // Return the CompletableFuture
        return future;
    }

    /**
     * Updates the username of a user.
     *
     * @param uid      The unique ID of the user.
     * @param username The new username.
     * @return {@code true} if the username was successfully changed, {@code false} otherwise.
     */
    @Override
    public boolean changeUsername(String uid, String username) {
        return updateField(uid, "username", username);
    }

    /**
     * Updates the email of a user.
     *
     * @param uid   The unique ID of the user.
     * @param email The new email.
     * @return {@code true} if the email was successfully changed, {@code false} otherwise.
     */
    @Override
    public boolean changeEmail(String uid, String email) {
        return updateField(uid, "email", email);
    }

    /**
     * Deletes a user account.
     *
     * @param userId The unique ID of the user to be deleted.
     * @return {@code true} if the account was successfully deleted, {@code false} otherwise.
     */
    @Override
    public boolean deleteAccount(String userId) {
        final boolean[] success = {false};

        usersRef.child(userId).removeValue((databaseError, databaseReference) -> {
            success[0] = databaseError == null;
        });

        return success[0];
    }

    /**
     * Finds a user by their credentials: username, email, and password.
     *
     * @param username The username of the user.
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return A CompletableFuture that will complete with the User entity if the credentials match, or null otherwise.
     */
    @Override
    public CompletableFuture<User> findUserByCredentials(String username, String email, String password) {
        CompletableFuture<User> future = new CompletableFuture<>();

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String dbUsername = userSnapshot.child("username").getValue(String.class);
                    String dbEmail = userSnapshot.child("email").getValue(String.class);
                    String dbPassword = userSnapshot.child("password").getValue(String.class);

                    if (dbUsername != null && dbEmail != null && dbPassword != null
                            && dbUsername.equals(username)
                            && dbEmail.equals(email)
                            && dbPassword.equals(password)) {
                        // Complete with the matched user
                        future.complete(mapSnapshotToUser(userSnapshot));
                        return;
                    }
                }
                // Complete with null if no user matches
                future.complete(null);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(new Exception("Database error: " + databaseError.getMessage()));
            }
        });

        // Return the CompletableFuture
        return future;
    }

    /**
     * Helper to update a user's field in Firebase.
     *
     * @param uid   The unique ID of the user.
     * @param field The field to update.
     * @param value The new value for the field.
     * @return {@code true} if the update was successful, {@code false} otherwise.
     */
    private boolean updateField(String uid, String field, String value) {
        final boolean[] success = {false};

        usersRef.child(uid).child(field).setValue(value, (databaseError, databaseReference) -> {
            success[0] = databaseError == null;
        });

        return success[0];
    }

    /**
     * Maps a DataSnapshot to a User entity.
     *
     * @param snapshot The snapshot of user data.
     * @return A User object populated with the snapshot's data.
     */
    private User mapSnapshotToUser(DataSnapshot snapshot) {
        String userId = snapshot.getKey();
        String username = snapshot.child("username").getValue(String.class);
        String email = snapshot.child("email").getValue(String.class);
        String password = snapshot.child("password").getValue(String.class);

        return new CommonUser(username, email, password, userId);
    }
}
