package interface_adapter.image;

import use_case.image.ImagePageOutputBoundary;

public class ImagePagePresenter implements ImagePageOutputBoundary {

    private final ImagePageViewModel viewModel;

    public ImagePagePresenter(ImagePageViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentImagePath(String path) {
        viewModel.setSelectedImagePath(path);
    }
}
