package use_case.image;

import data_access.ImageUploadDataAccess;
import java.io.File;
import java.io.IOException;

/**
 * The {@code ImagePageInteractor} class handles the core logic for image uploads,
 * delegating API interactions to the {@code ImageUploadDataAccess}.
 */
public class ImagePageInteractor implements ImagePageInputBoundary {

    private final ImagePageOutputBoundary outputBoundary;
    private final ImageUploadDataAccess imageUploadDataAccess;

    /**
     * Constructs an {@code ImagePageInteractor} with the specified output boundary and data access.
     *
     * @param outputBoundary       the output boundary for presenting results of the image upload process.
     * @param imageUploadDataAccess the data access class for interacting with the Imgur API.
     */
    public ImagePageInteractor(ImagePageOutputBoundary outputBoundary, ImageUploadDataAccess imageUploadDataAccess) {
        this.outputBoundary = outputBoundary;
        this.imageUploadDataAccess = imageUploadDataAccess;
    }

    /**
     * Uploads an image file using the data access layer with the specified description.
     * Communicates success or failure to the output boundary.
     *
     * @param imageFile   the {@code File} object representing the image to upload.
     * @param description a brief description of the image to include with the upload.
     * @throws IOException if an I/O error occurs during the upload process.
     */
    @Override
    public void uploadImage(File imageFile, String description) throws IOException {
        try {
            String response = imageUploadDataAccess.uploadImage(imageFile, description);
            outputBoundary.presentUploadSuccess("Image uploaded successfully: " + response);
        } catch (IllegalArgumentException e) {
            outputBoundary.presentUploadFailure(e.getMessage());
        } catch (IOException e) {
            outputBoundary.presentUploadFailure("Error during upload: " + e.getMessage());
            throw e;
        }
    }
}
