package data_access;

import entity.PhotoLocation;
import entity.CommonUser;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.dataAccessInterface.LocationDataAccess;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/**
 * Actual implementation of data access that fetches images and their corresponding locations,
 * and includes user-related functionalities.
 */
public class PhotoLocationDataAccess implements LocationDataAccess {

    private static final String IMGUR_API_URL = "https://api.imgur.com/3/account/me/images";
    private static final String ACCESS_TOKEN = "50ebc9d32abce50f92c2794ae7b36aa3e743b272";

    private final ArrayList<PhotoLocation> locations = new ArrayList<>();
    private final ArrayList<CommonUser> users = new ArrayList<>();
    private Random random;

    public PhotoLocationDataAccess(long seed) {
        random = new Random(seed);
        fetchImagesAndLocations();
    }

    public PhotoLocationDataAccess() {
        this(new Random().nextLong());
    }

    /**
     * Fetches images and their corresponding coordinates from the Imgur API.
     */
    private void fetchImagesAndLocations() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(IMGUR_API_URL)
                .addHeader("Authorization", "Bearer " + ACCESS_TOKEN)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JSONObject json = new JSONObject(responseBody);
                JSONArray imagesArray = json.getJSONArray("data");

                // Iterate through the fetched image data and generate PhotoLocation objects
                for (int i = 0; i < imagesArray.length(); i++) {
                    JSONObject imageData = imagesArray.getJSONObject(i);

                    String imageUrl = imageData.getString("link");
                    String description = imageData.optString("description", "");

                    // Parse coordinates from the description field if available
                    double[] coordinates = parseCoordinates(description);

                    if (coordinates != null) {
                        ImageIcon photo = new ImageIcon(new URL(imageUrl));
                        PhotoLocation photoLocation = new PhotoLocation(photo, coordinates, i + 1);
                        locations.add(photoLocation);
                    }
                }

                System.out.println("Fetched " + locations.size() + " photo locations from Imgur.");
            } else {
                System.err.println("Failed to fetch images: " + response.message());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error fetching images and locations.");
        }
    }

    /**
     * Parses the coordinates from the image description.
     *
     * @param description Description containing coordinates.
     * @return A double array representing latitude and longitude, or null if parsing fails.
     */
    private double[] parseCoordinates(String description) {
        try {
            // Example description format: "Coordinates: [43.659842, -79.397183]"
            if (description.startsWith("Coordinates:")) {
                String coords = description.substring(description.indexOf("[") + 1, description.indexOf("]"));
                String[] splitCoords = coords.split(",");
                double latitude = Double.parseDouble(splitCoords[0].trim());
                double longitude = Double.parseDouble(splitCoords[1].trim());
                return new double[]{latitude, longitude};
            }
        } catch (Exception e) {
            System.err.println("Failed to parse coordinates from description: " + description);
        }
        return null;
    }

    @Override
    public int getPhotoID() {
        return 0;
    }

    @Override
    public PhotoLocation getPhotoLocationByID(int id) {
        return null;
    }

    @Override
    public void setSeed(long seed) {
        random = new Random(seed);
    }

    @Override
    public PhotoLocation getRandomLocation() {
        if (locations.isEmpty()) {
            throw new IllegalStateException("No photo locations available.");
        }
        return locations.get(random.nextInt(locations.size()));
    }

    /**
     * Exposes the list of PhotoLocation objects for testing.
     *
     * @return List of PhotoLocation objects.
     */
    public ArrayList<PhotoLocation> getLocations() {
        return locations;
    }
}
