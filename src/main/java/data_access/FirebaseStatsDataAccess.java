/**
 * FirebaseStatsDataAccess.java
 *
 * This class provides an implementation of the `StatsDataAccess` interface for accessing user statistics
 * stored in a Firebase Realtime Database. It handles retrieving statistics data asynchronously and converting
 * it into a `StatsOutputData` object.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Clearly defines its role in retrieving user statistics from Firebase.
 * - **Clarity**: Describes the asynchronous nature of Firebase access and the use of CompletableFuture.
 * - **Completeness**: Covers initialization, query logic, and exception handling.
 * - **Ease of Use**: Demonstrates how to integrate the class with Firebase and use it in the application.
 * - **Up-to-Dateness**: Reflects the current implementation details for Firebase access.
 */

package data_access;

import com.google.firebase.database.*;
import use_case.dataAccessInterface.StatsDataAccess;
import use_case.stats.StatsOutputData;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of `StatsDataAccess` for accessing user statistics in Firebase.
 * Retrieves user statistics data and converts it into a `StatsOutputData` object.
 */
public class FirebaseStatsDataAccess implements StatsDataAccess {

    private final DatabaseReference database;

    /**
     * Constructs a `FirebaseStatsDataAccess` instance with the specified Firebase database reference.
     *
     * @param database The Firebase database reference for accessing user statistics data.
     *
     * Usage Example:
     * <pre>
     *     DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
     *     FirebaseStatsDataAccess dataAccess = new FirebaseStatsDataAccess(dbRef);
     *     StatsOutputData stats = dataAccess.getUserStats("user123");
     * </pre>
     */
    public FirebaseStatsDataAccess(DatabaseReference database) {
        this.database = database;
    }

    /**
     * Retrieves the statistics for a specified user from the Firebase database.
     *
     * The method queries the "users" node for a matching username and retrieves the associated statistics.
     * If the user is found, a `StatsOutputData` object is created with the retrieved data.
     * If the user is not found or if an error occurs, the method handles these scenarios gracefully.
     *
     * @param username The username of the user whose statistics are to be retrieved.
     * @return A `StatsOutputData` object containing the user's statistics, or `null` if the user is not found or an error occurs.
     *
     * Key Points:
     * - The method uses a `CompletableFuture` to handle asynchronous data retrieval.
     * - Errors and exceptions are logged, and the future is completed with appropriate results.
     *
     * Process:
     * 1. Adds a listener to the "users" node in Firebase.
     * 2. Iterates through user entries to find a matching username.
     * 3. Retrieves and processes statistics for the found user.
     * 4. Completes the future with the resulting `StatsOutputData`.
     * 5. Handles errors gracefully by completing the future with an exception or `null`.
     */
    @Override
    public StatsOutputData getUserStats(String username) {
        CompletableFuture<StatsOutputData> future = new CompletableFuture<>();

        database.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String name = userSnapshot.child("username").getValue(String.class);

                    if (username.equals(name)) {
                        int correctGuesses = userSnapshot.child("correctGuesses").getValue(Integer.class);
                        int gamesPlayed = userSnapshot.child("gamesPlayed").getValue(Integer.class);
                        int points = userSnapshot.child("points").getValue(Integer.class);

                        StatsOutputData stats = new StatsOutputData(username, points, gamesPlayed, correctGuesses);
                        future.complete(stats);
                        return;
                    }
                }

                System.err.println("User with username '" + username + "' not found.");
                future.complete(null);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error accessing Firebase: " + databaseError.getMessage());
                future.completeExceptionally(databaseError.toException());
            }
        });

        try {
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
