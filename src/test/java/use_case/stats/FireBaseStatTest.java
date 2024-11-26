package StatsTests;

import app.FirebaseInitializer;
import com.google.firebase.database.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FirebaseStatTest {

    private static DatabaseReference database;

    @BeforeAll
    static void initializeFirebase() throws Exception {
        // Step 1: Initialize Firebase
        FirebaseInitializer.initializeFirebase();

        // Step 2: Get database reference
        database = FirebaseDatabase.getInstance().getReference();
    }

    @Test
    void testRetrieveStatsForDemoUser() {
        // Step 3: Define the username to look up
        String targetUsername = "DemoUser";

        database.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean userFound = false;

                // Iterate through all users
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String username = userSnapshot.child("name").getValue(String.class);

                    if (targetUsername.equals(username)) {
                        // User found - retrieve their stats
                        int correctGuesses = userSnapshot.child("correctGuesses").getValue(Integer.class);
                        int gamesPlayed = userSnapshot.child("gamesPlayed").getValue(Integer.class);
                        int points = userSnapshot.child("points").getValue(Integer.class);

                        // Validate the retrieved information
                        assertEquals(3, correctGuesses, "Correct guesses should be 0");
                        assertEquals(0, gamesPlayed, "Games played should be 0");
                        assertEquals(0, points, "Points should be 0");

                        System.out.println("Test passed for user: " + username);
                        System.out.println("Correct Guesses: " + correctGuesses);
                        System.out.println("Games Played: " + gamesPlayed);
                        System.out.println("Points: " + points);

                        userFound = true;
                        break;
                    }
                }

                if (!userFound) {
                    fail("User with username '" + targetUsername + "' not found.");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                fail("Error accessing Firebase: " + databaseError.getMessage());
            }
        });

        // Add a brief wait to allow the async Firebase call to complete (optional for tests)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            fail("Test interrupted: " + e.getMessage());
        }
    }
}