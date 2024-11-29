package use_case.image;

public interface ImagePageOutputBoundary {
    void presentImagePath(String imagePath);
    void presentUploadSuccess(String response);
    void presentUploadFailure(String error);
}

