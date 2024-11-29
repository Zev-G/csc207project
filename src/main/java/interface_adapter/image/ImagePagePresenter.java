package interface_adapter.image;

import use_case.image.ImagePageOutputBoundary;

/**
 * This class implements the {@code ImagePageOutputBoundary} interface,
 * bridging the use case layer with the user interface. It formats and passes data to the {@code ImagePageViewModel}
 * to update the view.
 */
public class ImagePagePresenter implements ImagePageOutputBoundary {

    private final ImagePageViewModel viewModel;

    /**
     * Constructs an {@code ImagePagePresenter} with the specified {@code ImagePageViewModel}.
     *
     * @param viewModel the view model used to update the user interface with image-related data.
     */
    public ImagePagePresenter(ImagePageViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Handles the successful upload of an image and updates the view model with a success message.
     *
     * @param successMessage the message describing the success of the image upload.
     */
    @Override
    public void presentUploadSuccess(String successMessage) {
        viewModel.setUploadStatus("Success: " + successMessage);
    }

    /**
     * Handles a failed image upload and updates the view model with an error message.
     *
     * @param errorMessage the message describing the reason for the failure.
     */
    @Override
    public void presentUploadFailure(String errorMessage) {
        viewModel.setUploadStatus("Failure: " + errorMessage);
    }

    /**
     * Handles the image path selection and updates the view model with the selected image's path.
     *
     * @param path the file path of the selected image.
     */
    @Override
    public void presentImagePath(String path) {
        viewModel.setSelectedImagePath(path);
    }
}
