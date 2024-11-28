package use_case.image;

import okhttp3.*;
import java.io.File;
import java.io.IOException;

/**
 * Interactor for handling image upload functionality.
 */
public class ImagePageInteractor implements ImagePageInputBoundary {

    private static final String IMGUR_API_URL = "https://api.imgur.com/3/image";
    private final ImagePageOutputBoundary outputBoundary;
    private final String accessToken;

    /**
     * Constructor for ImagePageInteractor.
     *
     * @param outputBoundary The output boundary for presenting results.
     * @param accessToken    The access token for Imgur API authentication.
     */
    public ImagePageInteractor(ImagePageOutputBoundary outputBoundary, String accessToken) {
        this.outputBoundary = outputBoundary;
        this.accessToken = accessToken;
    }

    @Override
    public void uploadImage(File imageFile, String description) throws IOException {
        if (imageFile == null || !imageFile.exists()) {
            outputBoundary.presentUploadFailure("Image file does not exist.");
            return;
        }

        OkHttpClient client = new OkHttpClient();

        // Prepare request body for the image upload
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", imageFile.getName(),
                        RequestBody.create(imageFile, MediaType.parse("image/jpeg")))
                .addFormDataPart("description", description)
                .build();

        // Prepare the HTTP request
        Request request = new Request.Builder()
                .url(IMGUR_API_URL)
                .addHeader("Authorization", "Bearer " + accessToken)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                outputBoundary.presentUploadSuccess("Image uploaded successfully: " + responseBody);
            } else {
                String errorMessage = response.body() != null ? response.body().string() : "Unknown error";
                outputBoundary.presentUploadFailure("Failed to upload image: " + errorMessage);
            }
        } catch (Exception e) {
            outputBoundary.presentUploadFailure("Error during upload: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
