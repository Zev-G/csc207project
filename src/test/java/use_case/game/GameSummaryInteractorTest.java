package use_case.game;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameSummaryInteractorTest {

    static class TestOutputBoundary implements GameSummaryOutputBoundary {
        private GameSummaryOutputData receivedData;

        @Override
        public void presentStats(GameSummaryOutputData outputData) {
            this.receivedData = outputData;
        }

        public GameSummaryOutputData getReceivedData() {
            return receivedData;
        }
    }

    @Test
    void testFetchStats() {
        // Setup test data
        List<Boolean> guessBar = Arrays.asList(true, false, true);
        int points = 15;
        String username = "test_user";

        GameSummaryInputData inputData = new GameSummaryInputData(guessBar, points, username);
        TestOutputBoundary outputBoundary = new TestOutputBoundary();
        GameSummaryInteractor interactor = new GameSummaryInteractor(outputBoundary);

        // Execute method
        interactor.fetchStats(inputData);

        // Verify the results
        GameSummaryOutputData outputData = outputBoundary.getReceivedData();
        assertNotNull(outputData);
        assertEquals(username, outputData.getUsername());
        assertEquals(points, outputData.getPoints());
        assertEquals(guessBar, outputData.getGuesses());
    }
}
