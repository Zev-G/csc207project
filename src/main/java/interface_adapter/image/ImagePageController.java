package interface_adapter.image;

import use_case.image.ImagePageInputBoundary;

import java.io.File;
import java.io.IOException;

/**
 * This class serves as the controller in the interface adapter layer.
 * It acts as a mediator between the user interface and the use case interactor for image-related operations.
 */
public class ImagePageController {

    private final ImagePageInputBoundary interactor;

    /**
     * Constructs an {@code ImagePageController} with the specified {@code ImagePageInputBoundary}.
     *
     * @param interactor the input boundary that defines the use case logic for image-related operations.
     */
    public ImagePageController(ImagePageInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Uploads an image to the system by delegating the request to the interactor.
     *
     * @param imageFile   the {@code File} object representing the image to be uploaded.
     * @param description a brief description of the image being uploaded.
     * @throws IOException if an I/O error occurs while processing the image file.
     */
    public void uploadImage(File imageFile, String description) throws IOException {
        interactor.uploadImage(imageFile, description);
    }
}
