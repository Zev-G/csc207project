package data_access;

import com.google.firebase.database.*;
import use_case.stats.StatsOutputData;

public class FirebaseStatsDataAccess implements StatsDataAccess {

    private final DatabaseReference database;

    public FirebaseStatsDataAccess(DatabaseReference database) {
        this.database = database;
    }

    @Override
    public StatsOutputData getUserStats(String username) {
        final StatsOutputData[] result = {null}; // Placeholder for retrieved stats

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

                        // Build StatsOutputData object
                        result[0] = new StatsOutputData(username, points, gamesPlayed, correctGuesses);
                        break;
                    }
                }

                if (result[0] == null) {
                    System.err.println("User with username '" + username + "' not found.");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error accessing Firebase: " + databaseError.getMessage());
            }
        });

        // Wait to ensure the asynchronous operation completes
        try {
            Thread.sleep(3000); // Optional: Adjust time to wait for Firebase response
        } catch (InterruptedException e) {
            System.err.println("Error while waiting for Firebase response: " + e.getMessage());
        }

        return result[0];
    }
}
