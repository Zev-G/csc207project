package use_case.login;

import data_access.FirebaseLogInDataAccess;
import entity.CommonUser;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LogInInteractorTest {

    @Test
    void testAccountStateUpdatesOnSuccessfulLogin() {
        // Mock dependencies
        FirebaseLogInDataAccess mockDataAccess = mock(FirebaseLogInDataAccess.class);
        LogInOutputBoundary mockOutputBoundary = mock(LogInOutputBoundary.class);
        AccountViewModel accountViewModel = new AccountViewModel();

        // Set up interactor
        LogInInteractor interactor = new LogInInteractor(mockDataAccess, mockOutputBoundary, accountViewModel);

        // Mock successful login
        CommonUser mockUser = new CommonUser("testUser", "testPassword", "testEmail@example.com", "user123");
        when(mockDataAccess.findUserByCredentials("testUser", "testEmail@example.com", "testPassword"))
                .thenReturn(CompletableFuture.completedFuture(mockUser));

        // Input data
        LogInInputData inputData = new LogInInputData("testUser", "testEmail@example.com", "testPassword");

        // Run login
        interactor.logIn(inputData);

        // Verify AccountState
        AccountState updatedState = accountViewModel.getState();
        assertNotNull(updatedState);
        assertTrue(updatedState.isLoggedIn());
        assertEquals("testUser", updatedState.getUsername());
        assertEquals("testEmail@example.com", updatedState.getEmail());
        assertEquals("testPassword", updatedState.getPassword());
        assertEquals("user123", updatedState.getUserId());
    }
}
