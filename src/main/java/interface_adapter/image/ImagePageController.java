package interface_adapter.image;

import use_case.image.ImagePageInputBoundary;

public class ImagePageController {

    private final ImagePageInputBoundary interactor;

    public ImagePageController(ImagePageInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void uploadImage(String imagePath) {
        interactor.uploadImage(imagePath);
    }
}
