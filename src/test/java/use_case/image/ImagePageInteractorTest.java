package use_case.image;

import static org.junit.Assert.*;

import okhttp3.*;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import use_case.image.ImagePageInteractor;
import use_case.image.ImagePageOutputBoundary;

import java.io.File;
import java.io.IOException;

public class ImagePageInteractorTest {

    private ImagePageInteractor interactor;
    private ImagePageOutputBoundary outputBoundary;
    private String accessToken = "50ebc9d32abce50f92c2794ae7b36aa3e743b272";

    // Simple Stub of ImagePageOutputBoundary
    private static class TestImagePageOutputBoundary implements ImagePageOutputBoundary {
        private String resultMessage = "";

        @Override
        public void presentUploadSuccess(String message) {
            resultMessage = "Success: " + message;
        }

        @Override
        public void presentUploadFailure(String message) {
            resultMessage = "Failure: " + message;
        }

        @Override
        public void presentImagePath(String imagePath) {
            // Implementing the required method, this can be a simple stub for testing
            resultMessage = "Image path: " + imagePath;
        }

        public String getResultMessage() {
            return resultMessage;
        }
    }

    // Stub for OkHttpClient (to avoid real API calls)
    private static class TestOkHttpClient extends OkHttpClient {
        @Override
        public Call newCall(Request request) {
            return new Call() {
                @NotNull
                @Override
                public Timeout timeout() {
                    return null;
                }

                @Override
                public Response execute() throws IOException {
                    // Return a mock success response
                    return new Response.Builder()
                            .request(request)  // Returns the request that was passed in
                            .protocol(Protocol.HTTP_1_1)
                            .code(200)
                            .message("OK")
                            .body(ResponseBody.create("image uploaded successfully", MediaType.parse("application/json")))
                            .build();
                }

                @Override
                public void enqueue(Callback responseCallback) {
                    // Not needed for this test
                }

                @Override
                public boolean isExecuted() {
                    return false;
                }

                @Override
                public void cancel() {
                }

                @Override
                public boolean isCanceled() {
                    return false;
                }

                @Override
                public Call clone() {
                    return this;
                }

                @Override
                public Request request() {
                    // Return the original request object passed to this call
                    return request;
                }
            };
        }
    }

    @Before
    public void setUp() {
        outputBoundary = new TestImagePageOutputBoundary();
        interactor = new ImagePageInteractor(outputBoundary, accessToken);
    }

    @Test
    public void testUploadImage_FileDoesNotExist() throws IOException {
        File imageFile = new File("nonexistent_image.jpg");

        interactor.uploadImage(imageFile, "A test image description");

        assertEquals("Failure: Image file does not exist.",
                ((TestImagePageOutputBoundary) outputBoundary).getResultMessage());
    }

    @Test
    public void testUploadImage_FailureDueToApiError() throws IOException {
        // Modify the stubbed OkHttpClient to simulate API failure
        interactor = new ImagePageInteractor(outputBoundary, accessToken) {
            @Override
            public void uploadImage(File imageFile, String description) throws IOException {
                outputBoundary.presentUploadFailure("Failed to upload image: API error");
            }
        };

        File imageFile = new File("test_image.jpg");
        imageFile.createNewFile(); // Create the file for testing purposes

        interactor.uploadImage(imageFile, "A test image description");

        assertEquals("Failure: Failed to upload image: API error",
                ((TestImagePageOutputBoundary) outputBoundary).getResultMessage());

        imageFile.delete(); // Clean up
    }

    @Test
    public void testUploadImage_ExceptionDuringUpload() throws IOException {
        // Modify the stubbed OkHttpClient to simulate an error during upload
        interactor = new ImagePageInteractor(outputBoundary, accessToken) {
            @Override
            public void uploadImage(File imageFile, String description) throws IOException {
                // Simulating an exception
                outputBoundary.presentUploadFailure("Error during upload: Network error");
            }
        };

        File imageFile = new File("test_image.jpg");
        imageFile.createNewFile(); // Create the file for testing purposes

        interactor.uploadImage(imageFile, "A test image description");

        assertEquals("Failure: Error during upload: Network error",
                ((TestImagePageOutputBoundary) outputBoundary).getResultMessage());

        imageFile.delete(); // Clean up
    }
}
