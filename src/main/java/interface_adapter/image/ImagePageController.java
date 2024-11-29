package interface_adapter.image;

import use_case.image.ImagePageInputBoundary;

import java.io.File;
import java.io.IOException;

public class ImagePageController {

    private final ImagePageInputBoundary interactor;

    public ImagePageController(ImagePageInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void uploadImage(File imageFile, String description) throws IOException {
        interactor.uploadImage(imageFile, description);
    }
}
