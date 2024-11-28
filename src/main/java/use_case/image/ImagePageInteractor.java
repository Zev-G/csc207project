package use_case.image;

import okhttp3.*;
import java.io.File;
import java.io.IOException;

/**
 * Interactor for handling image uploads to Imgur and local simulations.
 */
public class ImagePageInteractor implements ImagePageInputBoundary {

    private static final String IMGUR_API_URL = "https://api.imgur.com/3/image";
    private final String accessToken;
    private final ImagePageOutputBoundary outputBoundary;

    /**
     * Constructor to initialize the interactor with the output boundary and access token.
     *
     * @param outputBoundary The output boundary for presenting results.
     * @param accessToken    The access token for Imgur API.
     */
    public ImagePageInteractor(ImagePageOutputBoundary outputBoundary, String accessToken) {
        this.outputBoundary = outputBoundary;
        this.accessToken = accessToken;
    }

    @Override
    public void uploadImage(String imagePath) {
        // Simulate the upload operation for local operations
        outputBoundary.presentImagePath(imagePath);
    }

    /**
     * Uploads an image to Imgur with a description.
     *
     * @param imageFile   The image file to upload.
     * @param description The description of the image.
     * @return The Imgur link to the uploaded image.
     * @throws IOException If the upload fails.
     */
    public String uploadImageToImgur(File imageFile, String description) throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", imageFile.getName(),
                        RequestBody.create(imageFile, MediaType.parse("image/jpeg")))
                .addFormDataPart("description", description)
                .build();

        Request request = new Request.Builder()
                .url(IMGUR_API_URL)
                .addHeader("Authorization", "Bearer " + accessToken)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                outputBoundary.presentUploadSuccess(responseBody); // Notify success via output boundary
                return responseBody; // Return the full response for further processing
            } else {
                String errorMessage = "Imgur upload failed: " + response.body().string();
                outputBoundary.presentUploadFailure(errorMessage); // Notify failure via output boundary
                throw new IOException(errorMessage);
            }
        }
    }
}
