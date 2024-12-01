package use_case.image;

/**
 * This interface defines the methods required for
 * presenting the results of image-related operations. It serves as the output boundary
 * in the use case layer, allowing data to be passed to the user interface layer.
 */
public interface ImagePageOutputBoundary {

    /**
     * Presents the path of the selected or uploaded image.
     *
     * @param imagePath the file path of the image as a {@code String}.
     */
    void presentImagePath(String imagePath);

    /**
     * Presents a success message for an image upload operation.
     *
     * @param response the response message indicating successful upload, typically including relevant details.
     */
    void presentUploadSuccess(String response);

    /**
     * Presents an error message for a failed image upload operation.
     *
     * @param error the error message describing the reason for the failure.
     */
    void presentUploadFailure(String error);
}
