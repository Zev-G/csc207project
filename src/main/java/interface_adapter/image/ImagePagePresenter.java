package interface_adapter.image;

import use_case.image.ImagePageOutputBoundary;

public class ImagePagePresenter implements ImagePageOutputBoundary {

    private final ImagePageViewModel viewModel;

    public ImagePagePresenter(ImagePageViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentUploadSuccess(String successMessage) {
        viewModel.setUploadStatus("Success: " + successMessage);
    }

    @Override
    public void presentUploadFailure(String errorMessage) {
        viewModel.setUploadStatus("Failure: " + errorMessage);
    }

    @Override
    public void presentImagePath(String path) {
        viewModel.setSelectedImagePath(path);
    }
}
