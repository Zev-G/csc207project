package use_case.stats;

import use_case.dataAccessInterface.StatsDataAccess;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

// Updated In-Memory Implementation for StatsDataAccess
class InMemoryStatsDataAccess implements StatsDataAccess {
    private final Map<String, StatsOutputData> database = new HashMap<>();

    public void addUser(String username, int points, int gamesPlayed, int correctGuesses) {
        database.put(username, new StatsOutputData(username, points, gamesPlayed, correctGuesses));
    }

    @Override
    public StatsOutputData getUserStats(String username) {
        return database.getOrDefault(username, new StatsOutputData(username, 0, 0, 0));
    }
}

// In-memory StatsOutputBoundary for testing
class InMemoryStatsOutputBoundary implements StatsOutputBoundary {
    private StatsOutputData receivedData;

    @Override
    public void presentStats(StatsOutputData outputData) {
        this.receivedData = outputData;
    }

    public StatsOutputData getReceivedData() {
        return receivedData;
    }
}

public class StatsInteractorTest {

    @Test
    void testFetchStats() {
        // Arrange
        InMemoryStatsDataAccess dataAccess = new InMemoryStatsDataAccess();
        dataAccess.addUser("test_user", 100, 5, 20);
        InMemoryStatsOutputBoundary outputBoundary = new InMemoryStatsOutputBoundary();
        StatsInteractor interactor = new StatsInteractor(dataAccess, outputBoundary);

        StatsInputData inputData = new StatsInputData("test_user");

        // Act
        interactor.fetchStats(inputData);

        // Assert
        StatsOutputData result = outputBoundary.getReceivedData();
        assertNotNull(result);
        assertEquals("test_user", result.getUsername());
        assertEquals(100, result.getPoints());
        assertEquals(5, result.getGamesPlayed());
        assertEquals(20, result.getCorrectGuesses());
    }

    @Test
    void testUpdateStats() {
        // Arrange
        InMemoryStatsDataAccess dataAccess = new InMemoryStatsDataAccess();
        dataAccess.addUser("test_user", 100, 5, 20);
        StatsRepository repository = (StatsRepository) dataAccess;
        UpdateStatsInteractor interactor = new UpdateStatsInteractor(repository);

        UpdateStatsInputData inputData = new UpdateStatsInputData("test_user", 50, 10);

        // Act
        interactor.updateStats(inputData);

        // Assert
        StatsOutputData updatedStats = dataAccess.getUserStats("test_user");
        assertNotNull(updatedStats);
        assertEquals("test_user", updatedStats.getUsername());
        assertEquals(150, updatedStats.getPoints());
        assertEquals(6, updatedStats.getGamesPlayed()); // gamesPlayed incremented
        assertEquals(30, updatedStats.getCorrectGuesses());
    }

    @Test
    void testFetchStatsForNonExistentUser() {
        // Arrange
        InMemoryStatsDataAccess dataAccess = new InMemoryStatsDataAccess();
        InMemoryStatsOutputBoundary outputBoundary = new InMemoryStatsOutputBoundary();
        StatsInteractor interactor = new StatsInteractor(dataAccess, outputBoundary);

        StatsInputData inputData = new StatsInputData("nonexistent_user");

        // Act
        interactor.fetchStats(inputData);

        // Assert
        StatsOutputData result = outputBoundary.getReceivedData();
        assertNotNull(result);
        assertEquals("nonexistent_user", result.getUsername());
        assertEquals(0, result.getPoints());
        assertEquals(0, result.getGamesPlayed());
        assertEquals(0, result.getCorrectGuesses());
    }
}
