package use_case.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.game.*;
import data_access.*;
import entity.*;

import static org.junit.jupiter.api.Assertions.*;

class GameInteractorTest {

    private GameInteractor interactor;
    private PhotoLocationDataAccess photoAccess;
    private TestPresenter presenter;

    @BeforeEach
    void setUp() {
        // Initialize PhotoLocationDataAccess with a fixed seed for deterministic results
        photoAccess = new PhotoLocationDataAccess(42L);

        // Use a custom TestPresenter to capture and validate output data
        presenter = new TestPresenter();

        // Initialize GameInteractor
        interactor = new GameInteractor(photoAccess, presenter);
    }

    @Test
    void testInit() {
        interactor.init();

        assertNotNull(presenter.getLastOutput());
        assertEquals(1, presenter.getLastOutput().getRound());
        assertNotNull(presenter.getLastOutput().getNextPhoto());
    }

    @Test
    void testHandleGuess() {
        interactor.init();

        PhotoLocation location = photoAccess.getLocations().get(0);
        GameInputData guessInput = new GameInputData(
                location.getPhotoID(),
                location.getLocation(),
                new double[]{43.659842, -79.397183} // Sample chosen location
        );

        interactor.handleGuess(guessInput);

        assertNotNull(presenter.getLastOutput());
    }

    @Test
    void testTimeout() {
        interactor.init();

        PhotoLocation location = photoAccess.getLocations().get(0);
        GameInputData timeoutInput = new GameInputData(
                location.getPhotoID(),
                location.getLocation(),
                new double[]{43.659842, -79.397183} // Sample chosen location
        );

        interactor.timeout(timeoutInput);

        assertNotNull(presenter.getLastOutput());
        assertTrue(presenter.getLastOutput().getRound() > 0);
    }

    @Test
    void testSetSeed() {
        interactor.setSeed(123L);

        // No direct output, ensure no exceptions and that a new random sequence is used
        assertDoesNotThrow(() -> interactor.init());
    }

    @Test
    void testEdgeCases() {
        // Test when no locations are available
        photoAccess.getLocations().clear(); // Clear all photo locations to simulate edge case
        Exception exception = assertThrows(IllegalStateException.class, interactor::init);
        assertEquals("No photo locations available.", exception.getMessage());
    }

    @Test
    void testEndGameEarly() {
        interactor.init();
        for (int i = 0; i < 10; i++) {
            PhotoLocation location = photoAccess.getLocations().get(0);
            GameInputData guessInput = new GameInputData(location.getPhotoID(), location.getLocation(), location.getLocation());
            interactor.handleGuess(guessInput);
        }
        assertEquals(11, presenter.getLastOutput().getRound());
    }

    // Custom presenter to capture output for assertions
    private static class TestPresenter implements GameOutputBoundary {

        private GameOutputData lastOutput;

        @Override
        public void init(GameOutputData data) {
            this.lastOutput = data;
        }

        @Override
        public void handleGuess(GameOutputData data) {
            this.lastOutput = data;
        }

        @Override
        public void endGame(GameOutputData data) {
            this.lastOutput = data;
        }

        public GameOutputData getLastOutput() {
            return lastOutput;
        }
    }
}
