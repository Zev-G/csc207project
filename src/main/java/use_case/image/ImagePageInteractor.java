package use_case.image;

import okhttp3.*;
import java.io.File;
import java.io.IOException;

/**
 * The {@code ImagePageInteractor} class handles the core logic for uploading images to an external service (Imgur API).
 * It implements the {@code ImagePageInputBoundary} interface and interacts with the {@code ImagePageOutputBoundary}
 * to present results back to the user interface layer.
 */
public class ImagePageInteractor implements ImagePageInputBoundary {

    private static final String IMGUR_API_URL = "https://api.imgur.com/3/image";
    private final ImagePageOutputBoundary outputBoundary;
    private final String accessToken;

    /**
     * Constructs an {@code ImagePageInteractor} with the specified output boundary and API access token.
     *
     * @param outputBoundary the output boundary for presenting results of the image upload process.
     * @param accessToken    the access token for authenticating requests to the Imgur API.
     */
    public ImagePageInteractor(ImagePageOutputBoundary outputBoundary, String accessToken) {
        this.outputBoundary = outputBoundary;
        this.accessToken = accessToken;
    }

    /**
     * Uploads an image file to the Imgur API with the specified description.
     * Validates the input file and communicates success or failure to the output boundary.
     *
     * @param imageFile   the {@code File} object representing the image to upload.
     * @param description a brief description of the image to include with the upload.
     * @throws IOException if an I/O error occurs during the upload process.
     */
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
