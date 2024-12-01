package use_case.image;

import java.io.File;
import java.io.IOException;

/**
 * This interface defines the input methods required for
 * the image-related use case operations. It provides an abstraction for uploading images.
 */
public interface ImagePageInputBoundary {

    /**
     * Uploads an image file with an associated description.
     *
     * @param imageFile   the {@code File} object representing the image to be uploaded.
     * @param description a brief description of the image being uploaded.
     * @throws IOException if an I/O error occurs during the image upload process.
     */
    void uploadImage(File imageFile, String description) throws IOException;
}
