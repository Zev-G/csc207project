package use_case.login;

import entity.CommonUser;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import data_access.FirebaseLogInDataAccess;
import interface_adapter.account.AccountViewModel;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;

class LogInInteractorTest {

    @Mock
    private FirebaseLogInDataAccess dataAccess;

    @Mock
    private LogInOutputBoundary outputBoundary;

    @Mock
    private AccountViewModel viewModel;

    private LogInInteractor interactor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new LogInInteractor(dataAccess, outputBoundary, viewModel);
    }

    @Test
    void testLogInWithEmptyFields() {
        // Arrange
        LogInInputData inputData = new LogInInputData("", "", "");

        // Act
        interactor.logIn(inputData);

        // Assert
        Mockito.verify(outputBoundary).present(Mockito.argThat(output ->
                !output.isSuccess() && "All fields are required.".equals(output.getMessage())
        ));
    }

    @Test
    void testLogInWithInvalidCredentials() {
        // Arrange
        LogInInputData inputData = new LogInInputData("username", "email", "password");
        Mockito.when(dataAccess.findUserByCredentials(any(), any(), any()))
                .thenReturn(CompletableFuture.completedFuture(null));

        // Act
        interactor.logIn(inputData);

        // Assert
        Mockito.verify(outputBoundary).present(Mockito.argThat(output ->
                !output.isSuccess() && "Invalid credentials!".equals(output.getMessage())
        ));
    }

    @Test
    void testLogInWithValidCredentials() {
        // Arrange
        LogInInputData inputData = new LogInInputData("username", "email", "password");
        User mockUser = new CommonUser("username", "password", "email", "userId");
        Mockito.when(dataAccess.findUserByCredentials(any(), any(), any()))
                .thenReturn(CompletableFuture.completedFuture(mockUser));

        // Act
        interactor.logIn(inputData);

        // Assert
        Mockito.verify(viewModel).setState(Mockito.any());
        Mockito.verify(outputBoundary).present(Mockito.argThat(output ->
                output.isSuccess() && "Log-in successful!".equals(output.getMessage())
        ));
        Mockito.verify(outputBoundary).onLoginSuccess();
    }

    @Test
    void testLogInWithException() {
        // Arrange
        LogInInputData inputData = new LogInInputData("username", "email", "password");
        Mockito.when(dataAccess.findUserByCredentials(any(), any(), any()))
                .thenReturn(CompletableFuture.failedFuture(new Exception("Database error")));

        // Act
        interactor.logIn(inputData);

        // Assert
        Mockito.verify(outputBoundary).present(Mockito.argThat(output ->
                !output.isSuccess() && output.getMessage().contains("Database error")
        ));
    }
}
