package use_case.stats;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

// In-memory implementation of StatsRepository for testing
class InMemoryStatsRepository implements StatsRepository {
    private final Map<String, StatsOutputData> database = new HashMap<>();

    public void addUser(String username, int points, int gamesPlayed, int correctGuesses) {
        database.put(username, new StatsOutputData(username, points, gamesPlayed, correctGuesses));
    }


    public StatsOutputData getUserStats(String username) {
        return database.getOrDefault(username, new StatsOutputData(username, 0, 0, 0));
    }

    @Override
    public void updateStats(String username, int points, int correctGuesses) {
        StatsOutputData existing = database.getOrDefault(username, new StatsOutputData(username, 0, 0, 0));
        int updatedPoints = existing.getPoints() + points;
        int updatedGamesPlayed = existing.getGamesPlayed() + 1;
        int updatedCorrectGuesses = existing.getCorrectGuesses() + correctGuesses;
        database.put(username, new StatsOutputData(username, updatedPoints, updatedGamesPlayed, updatedCorrectGuesses));
    }
}

public class UpdateStatsInteractorTest {

    @Test
    void testUpdateStatsForExistingUser() {
        // Arrange
        InMemoryStatsRepository repository = new InMemoryStatsRepository();
        repository.addUser("existing_user", 100, 5, 20);
        UpdateStatsInteractor interactor = new UpdateStatsInteractor(repository);

        UpdateStatsInputData inputData = new UpdateStatsInputData("existing_user", 50, 10);

        // Act
        interactor.updateStats(inputData);

        // Assert
        StatsOutputData updatedStats = repository.getUserStats("existing_user");
        assertNotNull(updatedStats);
        assertEquals("existing_user", updatedStats.getUsername());
        assertEquals(150, updatedStats.getPoints());
        assertEquals(6, updatedStats.getGamesPlayed()); // gamesPlayed incremented
        assertEquals(30, updatedStats.getCorrectGuesses());
    }

    @Test
    void testUpdateStatsForNewUser() {
        // Arrange
        InMemoryStatsRepository repository = new InMemoryStatsRepository();
        UpdateStatsInteractor interactor = new UpdateStatsInteractor(repository);

        UpdateStatsInputData inputData = new UpdateStatsInputData("new_user", 20, 5);

        // Act
        interactor.updateStats(inputData);

        // Assert
        StatsOutputData updatedStats = repository.getUserStats("new_user");
        assertNotNull(updatedStats);
        assertEquals("new_user", updatedStats.getUsername());
        assertEquals(20, updatedStats.getPoints());
        assertEquals(1, updatedStats.getGamesPlayed()); // gamesPlayed starts at 1 for new users
        assertEquals(5, updatedStats.getCorrectGuesses());
    }

    @Test
    void testUpdateStatsWithZeroPointsAndCorrectGuesses() {
        // Arrange
        InMemoryStatsRepository repository = new InMemoryStatsRepository();
        repository.addUser("test_user", 100, 5, 20);
        UpdateStatsInteractor interactor = new UpdateStatsInteractor(repository);

        UpdateStatsInputData inputData = new UpdateStatsInputData("test_user", 0, 0);

        // Act
        interactor.updateStats(inputData);

        // Assert
        StatsOutputData updatedStats = repository.getUserStats("test_user");
        assertNotNull(updatedStats);
        assertEquals("test_user", updatedStats.getUsername());
        assertEquals(100, updatedStats.getPoints()); // No change in points
        assertEquals(6, updatedStats.getGamesPlayed()); // gamesPlayed incremented
        assertEquals(20, updatedStats.getCorrectGuesses()); // No change in correct guesses
    }
}
