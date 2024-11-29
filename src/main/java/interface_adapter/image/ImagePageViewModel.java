package interface_adapter.image;

/**
 * This class represents the view model for the image page.
 * It acts as a container for data related to the user's interaction with the image page,
 * including the selected image path and the status of image upload operations.
 */
public class ImagePageViewModel {

    private String selectedImagePath;
    private String uploadStatus;

    /**
     * Gets the path of the currently selected image.
     *
     * @return the file path of the selected image as a {@code String}.
     */
    public String getSelectedImagePath() {
        return selectedImagePath;
    }

    /**
     * Sets the path of the currently selected image.
     *
     * @param selectedImagePath the file path of the selected image as a {@code String}.
     */
    public void setSelectedImagePath(String selectedImagePath) {
        this.selectedImagePath = selectedImagePath;
    }

    /**
     * Gets the status of the last image upload operation.
     *
     * @return the upload status as a {@code String}.
     */
    public String getUploadStatus() {
        return uploadStatus;
    }

    /**
     * Sets the status of the last image upload operation.
     *
     * @param uploadStatus the upload status as a {@code String}, typically indicating success or failure.
     */
    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }
}
