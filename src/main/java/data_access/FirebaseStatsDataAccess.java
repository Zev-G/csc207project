package data_access;

import com.google.firebase.database.*;
import use_case.stats.StatsOutputData;
import java.util.concurrent.CompletableFuture;

public class FirebaseStatsDataAccess implements StatsDataAccess {

    private final DatabaseReference database;

    public FirebaseStatsDataAccess(DatabaseReference database) {
        this.database = database;
    }

    @Override
    public StatsOutputData getUserStats(String username) {
        // Create a CompletableFuture to hold the result
        CompletableFuture<StatsOutputData> future = new CompletableFuture<>();

        // Query the database for all users
        database.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Iterate through all users
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String name = userSnapshot.child("name").getValue(String.class);

                    if (username.equals(name)) {
                        // User found - retrieve stats
                        int correctGuesses = userSnapshot.child("correctGuesses").getValue(Integer.class);
                        int gamesPlayed = userSnapshot.child("gamesPlayed").getValue(Integer.class);
                        int points = userSnapshot.child("points").getValue(Integer.class);

                        // Build StatsOutputData object and complete the future
                        StatsOutputData stats = new StatsOutputData(username, points, gamesPlayed, correctGuesses);
                        future.complete(stats);
                        return; // Exit the loop once the user is found
                    }
                }

                // If no user is found, complete the future with null
                System.err.println("User with username '" + username + "' not found.");
                future.complete(null);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error accessing Firebase: " + databaseError.getMessage());
                future.completeExceptionally(databaseError.toException()); // Complete exceptionally on error
            }
        });

        // Return the result from the CompletableFuture
        try {
            return future.get(); // This will block until the operation is complete (without sleep)
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null if there is an exception
        }
    }
}
