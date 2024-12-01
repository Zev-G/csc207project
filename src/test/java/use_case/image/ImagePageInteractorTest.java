//package use_case.image;
//
//import data_access.ImageUploadDataAccess;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Test class for {@link ImagePageInteractor}.
// */
//class ImagePageInteractorTest {
//
//    /**
//     * A mock implementation of the {@link ImagePageOutputBoundary} for testing purposes.
//     */
//    private static class TestImageOutputBoundary implements ImagePageOutputBoundary {
//
//        private String lastSuccessMessage;
//        private String lastErrorMessage;
//
//        @Override
//        public void presentImagePath(String imagePath) {
//            // Not needed for this test suite
//        }
//
//        @Override
//        public void presentUploadSuccess(String response) {
//            lastSuccessMessage = response;
//        }
//
//        @Override
//        public void presentUploadFailure(String error) {
//            lastErrorMessage = error;
//        }
//
//        public String getLastSuccessMessage() {
//            return lastSuccessMessage;
//        }
//
//        public String getLastErrorMessage() {
//            return lastErrorMessage;
//        }
//    }
//
//    /**
//     * A mock implementation of {@link ImageUploadDataAccess} for testing purposes.
//     */
//    private static class TestImageUploadDataAccess extends ImageUploadDataAccess {
//        public TestImageUploadDataAccess() {
//            super("TestAccessToken");
//        }
//
//        @Override
//        public String uploadImage(File imageFile, String description) throws IOException {
//            if (imageFile == null || !imageFile.exists()) {
//                throw new IOException("Image file does not exist.");
//            }
//            if ("Error description".equals(description)) {
//                throw new IOException("Simulated API error");
//            }
//            return "Simulated successful response";
//        }
//    }
//
//    @Test
//    void testUploadImage_Success() {
//        ImagePageOutputBoundary outputBoundary = new TestImageOutputBoundary();
//        ImageUploadDataAccess uploadDataAccess = new TestImageUploadDataAccess();
//        ImagePageInteractor interactor = new ImagePageInteractor(outputBoundary, uploadDataAccess);
//
//        // Load the test file dynamically
//        File validFile = new File(getClass().getClassLoader().getResource("photos/sample1.jpg").getFile());
//
//        try {
//            interactor.uploadImage(validFile, "Test description");
//            assertEquals("Image uploaded successfully: Simulated successful response",
//                    ((TestImageOutputBoundary) outputBoundary).getLastSuccessMessage());
//        } catch (IOException e) {
//            fail("Exception should not have been thrown for a valid file: " + e.getMessage());
//        }
//    }
//
//
//    @Test
//    void testUploadImage_FileDoesNotExist() {
//        ImagePageOutputBoundary outputBoundary = new TestImageOutputBoundary();
//        ImageUploadDataAccess uploadDataAccess = new TestImageUploadDataAccess();
//        ImagePageInteractor interactor = new ImagePageInteractor(outputBoundary, uploadDataAccess);
//
//        File nonExistentFile = new File("nonexistent.jpg");
//
//        try {
//            interactor.uploadImage(nonExistentFile, "Test description");
//            fail("Expected IOException was not thrown");
//        } catch (IOException e) {
//            assertEquals("Error during upload: Image file does not exist.",
//                    ((TestImageOutputBoundary) outputBoundary).getLastErrorMessage());
//        }
//    }
//
//    @Test
//    void testUploadImage_ApiThrowsException() {
//        ImagePageOutputBoundary outputBoundary = new TestImageOutputBoundary();
//        ImageUploadDataAccess uploadDataAccess = new TestImageUploadDataAccess();
//        ImagePageInteractor interactor = new ImagePageInteractor(outputBoundary, uploadDataAccess);
//
//        File validFile = new File("src/test/resources/photos/sample1.jpg");
//
//        try {
//            interactor.uploadImage(validFile, "Error description");
//            fail("Expected IOException was not thrown");
//        } catch (IOException e) {
//            assertEquals("Error during upload: Simulated API error",
//                    ((TestImageOutputBoundary) outputBoundary).getLastErrorMessage());
//        }
//    }
//}
