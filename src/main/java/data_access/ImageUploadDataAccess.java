package data_access;

import okhttp3.*;
import java.io.File;
import java.io.IOException;

/**
 * The {@code ImageUploadDataAccess} class handles the Imgur API interaction for uploading images.
 */
public class ImageUploadDataAccess {

    private static final String IMGUR_API_URL = "https://api.imgur.com/3/image";
    private final String accessToken;

    /**
     * Constructs an {@code ImageUploadDataAccess} instance with the specified access token.
     *
     * @param accessToken the access token for authenticating requests to the Imgur API.
     */
    public ImageUploadDataAccess(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Uploads an image file to the Imgur API with the specified description.
     *
     * @param imageFile   the {@code File} object representing the image to upload.
     * @param description a brief description of the image to include with the upload.
     * @return the API response body as a string if the upload is successful.
     * @throws IOException if an I/O error occurs during the upload process.
     */
    public String uploadImage(File imageFile, String description) throws IOException {
        if (imageFile == null || !imageFile.exists()) {
            throw new IllegalArgumentException("Image file does not exist.");
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
                return response.body().string();
            } else {
                String errorMessage = response.body() != null ? response.body().string() : "Unknown error";
                throw new IOException("Failed to upload image: " + errorMessage);
            }
        }
    }
}
