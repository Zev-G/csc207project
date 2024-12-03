package use_case.image;

import data_access.ImageUploadDataAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@code ImagePageInteractor} class.
 */
class ImagePageInteractorTest {

    private ImagePageOutputBoundary mockOutputBoundary;
    private ImageUploadDataAccess mockImageUploadDataAccess;
    private ImagePageInteractor interactor;

    @BeforeEach
    void setUp() {
        mockOutputBoundary = mock(ImagePageOutputBoundary.class);
        mockImageUploadDataAccess = mock(ImageUploadDataAccess.class);
        interactor = new ImagePageInteractor(mockOutputBoundary, mockImageUploadDataAccess);
    }

    @Test
    void testUploadImage_Success() throws IOException {
        // Arrange
        File imageFile = new File("test-image.jpg");
        String description = "Test description";
        String expectedResponse = "http://imgur.com/test-image";

        when(mockImageUploadDataAccess.uploadImage(imageFile, description)).thenReturn(expectedResponse);

        // Act
        interactor.uploadImage(imageFile, description);

        // Assert
        verify(mockImageUploadDataAccess).uploadImage(imageFile, description);
        verify(mockOutputBoundary).presentUploadSuccess("Image uploaded successfully: " + expectedResponse);
    }

    @Test
    void testUploadImage_InvalidArgument() throws IOException {
        // Arrange
        File imageFile = new File("invalid-image.jpg");
        String description = "Invalid description";
        String expectedError = "Invalid image file.";

        when(mockImageUploadDataAccess.uploadImage(imageFile, description))
                .thenThrow(new IllegalArgumentException(expectedError));

        // Act
        interactor.uploadImage(imageFile, description);

        // Assert
        verify(mockImageUploadDataAccess).uploadImage(imageFile, description);
        verify(mockOutputBoundary).presentUploadFailure(expectedError);
    }

    @Test
    void testUploadImage_IOException() throws IOException {
        // Arrange
        File imageFile = new File("test-image.jpg");
        String description = "Test description";
        String expectedError = "I/O error occurred.";

        // Mock setup to throw IOException
        doThrow(new IOException(expectedError)).when(mockImageUploadDataAccess).uploadImage(imageFile, description);

        // Act & Assert
        IOException thrown = assertThrows(IOException.class, () -> interactor.uploadImage(imageFile, description));
        assertEquals(expectedError, thrown.getMessage());

        verify(mockImageUploadDataAccess).uploadImage(imageFile, description);
        verify(mockOutputBoundary).presentUploadFailure("Error during upload: " + expectedError);
    }


    @Test
    void testUploadImage_NullFile() {
        // Arrange
        File imageFile = null;
        String description = "Test description";

        // Act & Assert
        assertThrows(NullPointerException.class, () -> interactor.uploadImage(imageFile, description));
        verifyNoInteractions(mockOutputBoundary, mockImageUploadDataAccess);
    }
}
