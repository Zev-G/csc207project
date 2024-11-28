package data_access;

import com.google.firebase.database.*;
import use_case.stats.StatsRepository;

public class FireBaseStatsUpdate implements StatsRepository {

    private final DatabaseReference database;

    public FireBaseStatsUpdate(DatabaseReference database) {
        this.database = database;
    }

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