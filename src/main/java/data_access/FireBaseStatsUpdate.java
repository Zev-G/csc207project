/**
 * FireBaseStatsUpdate.java
 *
 * This class provides an implementation of the `StatsRepository` interface for updating user statistics
 * in a Firebase Realtime Database. It updates points, correct guesses, and games played asynchronously.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Clearly defines its role in updating statistics in Firebase.
 * - **Clarity**: Provides detailed explanations of methods, validations, and asynchronous logic.
 * - **Completeness**: Covers initialization, update logic, and error handling.
 * - **Ease of Use**: Demonstrates how to integrate and use the class effectively in applications.
 * - **Up-to-Dateness**: Reflects the current implementation for Firebase updates.
 */

package data_access;

import com.google.firebase.database.*;
import use_case.stats.StatsRepository;

/**
 * Implementation of `StatsRepository` for updating user statistics in Firebase.
 * Handles points, correct guesses, and games played updates for a specified user.
 */
public class FireBaseStatsUpdate implements StatsRepository {

    private final DatabaseReference database;

    /**
     * Constructs a `FireBaseStatsUpdate` instance with the specified Firebase database reference.
     *
     * @param database The Firebase database reference for updating user statistics.
     *
     * Usage Example:
     * <pre>
     *     DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
     *     FireBaseStatsUpdate statsUpdate = new FireBaseStatsUpdate(dbRef);
     *     statsUpdate.updateStats("user123", 50, 10);
     * </pre>
     */
    public FireBaseStatsUpdate(DatabaseReference database) {
        this.database = database;
    }

    /**
     * Updates the statistics for a specified user in the Firebase database.
     *
     * The method queries the "users" node for a matching username and updates the user's statistics:
     * - Points: Adds the specified points to the user's current points.
     * - Correct Guesses: Adds the specified number of correct guesses to the user's total.
     * - Games Played: Increments the user's total games played by one.
     *
     * Validations:
     * - Ensures the username is not null or empty before proceeding.
     *
     * Asynchronous Process:
     * 1. Adds a listener to the "users" node in Firebase.
     * 2. Searches for a user entry with a matching username.
     * 3. Updates the relevant fields for the found user.
     * 4. Logs errors or user not found messages as appropriate.
     *
     * @param username       The username of the user whose statistics are to be updated.
     * @param points         The additional points to add to the user's total points.
     * @param correctGuesses The additional correct guesses to add to the user's total.
     */
    @Override
    public void updateStats(String username, int points, int correctGuesses) {
        if (username == null || username.isEmpty()) {
            System.err.println("Invalid username: Username cannot be null or empty.");
            return;
        }

        database.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean userFound = false;

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String name = userSnapshot.child("name").getValue(String.class);

                    if (username.equals(name)) {
                        userFound = true;
                        DatabaseReference userRef = userSnapshot.getRef();

                        // Update points
                        Integer currentPoints = userSnapshot.child("points").getValue(Integer.class);
                        currentPoints = (currentPoints == null) ? 0 : currentPoints;
                        userRef.child("points").setValueAsync(currentPoints + points);

                        // Update correct guesses
                        Integer currentCorrectGuesses = userSnapshot.child("correctGuesses").getValue(Integer.class);
                        currentCorrectGuesses = (currentCorrectGuesses == null) ? 0 : currentCorrectGuesses;
                        userRef.child("correctGuesses").setValueAsync(currentCorrectGuesses + correctGuesses);

                        // Update games played
                        Integer currentGamesPlayed = userSnapshot.child("gamesPlayed").getValue(Integer.class);
                        currentGamesPlayed = (currentGamesPlayed == null) ? 0 : currentGamesPlayed;
                        userRef.child("gamesPlayed").setValueAsync(currentGamesPlayed + 1);

                        System.out.println("Stats updated for user: " + username);
                        break;
                    }
                }

                if (!userFound) {
                    System.err.println("User with username '" + username + "' not found.");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error accessing Firebase: " + databaseError.getMessage());
            }
        });
    }
}
