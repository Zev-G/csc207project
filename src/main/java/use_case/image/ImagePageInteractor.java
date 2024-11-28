package use_case.image;

public class ImagePageInteractor implements ImagePageInputBoundary {

    private final ImagePageOutputBoundary outputBoundary;

    public ImagePageInteractor(ImagePageOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void uploadImage(String imagePath) {
        // Simulate an upload operation
        // In a real application, this could involve saving the image path to a database or cloud storage
        outputBoundary.presentImagePath(imagePath);
    }
}
