package use_case.signup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import use_case.dataAccessInterface.SignUpDataAccess;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SignUpInteractorTest {

    @Mock
    private SignUpDataAccess dataAccess;

    @Mock
    private SignUpOutputBoundary outputBoundary;

    private SignUpInteractor interactor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new SignUpInteractor(dataAccess, outputBoundary);
    }

    @Test
    void testSignUpWithValidInput() {
        // Arrange
        SignUpInputData inputData = new SignUpInputData("username", "email", "password");
        doNothing().when(dataAccess).createUser(any(), any());

        // Act
        interactor.execute(inputData);

        // Assert
        verify(dataAccess).createUser(anyString(), eq(inputData));
        verify(outputBoundary).present(Mockito.argThat(output ->
                output.isSuccess() && output.getErrorMessage() == null
        ));
    }

    @Test
    void testSignUpWithEmptyFields() {
        // Arrange
        SignUpInputData inputData = new SignUpInputData("", "email", "password");

        // Act
        interactor.execute(inputData);

        // Assert
        verify(outputBoundary).present(Mockito.argThat(output ->
                !output.isSuccess() && "Username cannot be empty.".equals(output.getErrorMessage())
        ));
    }

    @Test
    void testSignUpWithDuplicateUser() {
        // Arrange
        SignUpInputData inputData = new SignUpInputData("username", "email", "password");
        doThrow(new RuntimeException("User already exists")).when(dataAccess).createUser(anyString(), eq(inputData));

        // Act
        interactor.execute(inputData);

        // Assert
        verify(outputBoundary).present(Mockito.argThat(output ->
                !output.isSuccess() && "User already exists".equals(output.getErrorMessage())
        ));
    }

    @Test
    void testSignUpWithExceptionDuringValidation() {
        // Arrange
        SignUpInputData inputData = new SignUpInputData("username", "", "password");

        // Act
        interactor.execute(inputData);

        // Assert
        verify(outputBoundary).present(Mockito.argThat(output ->
                !output.isSuccess() && "Email cannot be empty.".equals(output.getErrorMessage())
        ));
    }
}
