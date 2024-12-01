package data_access;

import com.google.firebase.database.*;
import entity.CommonUser;
import entity.User;
import use_case.dataAccessInterface.UserDataAccess;

import java.util.concurrent.CountDownLatch;

/**
 * Firebase implementation for managing log-in functionality and user data.
 */
public class FirebaseLogInDataAccess implements UserDataAccess {
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
     * @param uid The unique ID of the user.
     * @return The user object associated with the ID.
     */
    @Override
    public User getUser(int uid) {
        final User[] userResult = {null};
        CountDownLatch latch = new CountDownLatch(1);

        usersRef.child(String.valueOf(uid)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    userResult[0] = mapSnapshotToUser(dataSnapshot);
                }
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                latch.countDown();
            }
        });

        waitForLatch(latch);
        return userResult[0];
    }

    /**
     * Updates the username of a user.
     *
     * @param uid      The unique ID of the user.
     * @param username The new username.
     * @return {@code true} if the username was successfully changed, {@code false} otherwise.
     */
    @Override
    public boolean changeUsername(int uid, String username) {
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
    public boolean changeEmail(int uid, String email) {
        return updateField(uid, "email", email);
    }

    /**
     * Deletes a user account.
     *
     * @param userId The unique ID of the user to be deleted.
     * @return {@code true} if the account was successfully deleted, {@code false} otherwise.
     */
    @Override
    public boolean deleteAccount(int userId) {
        final boolean[] success = {false};
        CountDownLatch latch = new CountDownLatch(1);

        usersRef.child(String.valueOf(userId)).removeValue((databaseError, databaseReference) -> {
            success[0] = databaseError == null;
            latch.countDown();
        });

        waitForLatch(latch);
        return success[0];
    }

    /**
     * Finds a user by their credentials: username, email, and password.
     *
     * @param username The username of the user.
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return User entity if the credentials match; null otherwise.
     */
    public User findUserByCredentials(String username, String email, String password) {
        final User[] userResult = {null};
        CountDownLatch latch = new CountDownLatch(1);

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
                        userResult[0] = mapSnapshotToUser(userSnapshot);
                        break;
                    }
                }
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                latch.countDown();
            }
        });

        waitForLatch(latch);
        return userResult[0];
    }

    /**
     * Helper to update a user's field in Firebase.
     *
     * @param uid   The unique ID of the user.
     * @param field The field to update.
     * @param value The new value for the field.
     * @return {@code true} if the update was successful, {@code false} otherwise.
     */
    private boolean updateField(int uid, String field, String value) {
        final boolean[] success = {false};
        CountDownLatch latch = new CountDownLatch(1);

        usersRef.child(String.valueOf(uid)).child(field).setValue(value, (databaseError, databaseReference) -> {
            success[0] = databaseError == null;
            latch.countDown();
        });

        waitForLatch(latch);
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
        int correctGuesses = snapshot.child("correctGuesses").getValue(Integer.class);
        int gamesPlayed = snapshot.child("gamesPlayed").getValue(Integer.class);
        int points = snapshot.child("points").getValue(Integer.class);

        return new CommonUser(username, email, password, userId, correctGuesses, gamesPlayed, points);
    }

    /**
     * Waits for a latch to count down to 0.
     *
     * @param latch The CountDownLatch to wait on.
     */
    private void waitForLatch(CountDownLatch latch) {
        try {
            latch.await();
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
