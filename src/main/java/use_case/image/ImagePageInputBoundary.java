package use_case.image;

import java.io.File;
import java.io.IOException;

public interface ImagePageInputBoundary {
    void uploadImage(File imageFile, String description) throws IOException;
}
