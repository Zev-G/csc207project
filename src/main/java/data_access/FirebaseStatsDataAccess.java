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
        final StatsOutputData[] result = new StatsOutputData[1];

        // Firebase query
        database.child("users").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int points = dataSnapshot.child("points").getValue(Integer.class);
                int gamesPlayed = dataSnapshot.child("gamesPlayed").getValue(Integer.class);
                int correctGuesses = dataSnapshot.child("correctGuesses").getValue(Integer.class);

                // Build StatsOutputData object
                result[0] = new StatsOutputData(username, points, gamesPlayed, correctGuesses);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw new RuntimeException("Error retrieving data from Firebase: " + databaseError.getMessage());
            }
        });

        return result[0];
    }
}