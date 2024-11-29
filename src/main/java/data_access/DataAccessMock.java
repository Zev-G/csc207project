package data_access;

import entity.PhotoLocation;
import entity.CommonUser;
import entity.User;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is for testing, will not be used in production.
 */
public class DataAccessMock implements LocationDataAccess, UserDataAccess {

    private static final String IMGUR_API_URL = "https://api.imgur.com/3/account/me/images";
    private static final String ACCESS_TOKEN = "50ebc9d32abce50f92c2794ae7b36aa3e743b272";

    private final ArrayList<PhotoLocation> locations = new ArrayList<>();
    private final ArrayList<CommonUser> users = new ArrayList<>();

    private Random random;

    public DataAccessMock(long seed) {
        random = new Random(seed);

        // Fetch images from Imgur and use them for predefined PhotoLocation objects
        fetchImagesFromImgur();

        // Users
        users.add(new CommonUser("Zev", "1234", "godfreyzev@gmail.com", 1));
    }

    public DataAccessMock() {
        this(new Random().nextLong());
    }

    private void fetchImagesFromImgur() {
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

                // Ensure we have at least four images for the predefined PhotoLocation objects
                if (imagesArray.length() >= 4) {
                    // Use the first four images from Imgur for the predefined PhotoLocation objects
                    JSONObject image1 = imagesArray.getJSONObject(0);
                    JSONObject image2 = imagesArray.getJSONObject(1);
                    JSONObject image3 = imagesArray.getJSONObject(2);
                    JSONObject image4 = imagesArray.getJSONObject(3);

                    String imageUrl1 = image1.getString("link");
                    String imageUrl2 = image2.getString("link");
                    String imageUrl3 = image3.getString("link");
                    String imageUrl4 = image4.getString("link");

                    ImageIcon photo1 = new ImageIcon(new URL(imageUrl1));
                    PhotoLocation photoLocation1 = new PhotoLocation(photo1, new double[]{43.66742755781882, -79.39177102147445}, 1);

                    ImageIcon photo2 = new ImageIcon(new URL(imageUrl2));
                    PhotoLocation photoLocation2 = new PhotoLocation(photo2, new double[]{43.65984277958618, -79.39718377820866}, 2);

                    ImageIcon photo3 = new ImageIcon(new URL(imageUrl3));
                    PhotoLocation photoLocation3 = new PhotoLocation(photo3, new double[]{43.662891, -79.395656}, 3);

                    ImageIcon photo4 = new ImageIcon(new URL(imageUrl4));
                    PhotoLocation photoLocation4 = new PhotoLocation(photo4, new double[]{43.665123, -79.400456}, 4);

                    locations.add(photoLocation1);
                    locations.add(photoLocation2);
                    locations.add(photoLocation3);
                    locations.add(photoLocation4);

                    System.out.println("Predefined PhotoLocation objects updated with Imgur images.");
                } else {
                    System.err.println("Not enough images available from Imgur to update predefined PhotoLocation objects.");
                }
            } else {
                System.err.println("Failed to fetch images from Imgur: " + response.message());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error fetching images from Imgur.");
        }
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
        return locations.get(random.nextInt(locations.size()));
    }

    @Override
    public boolean changeUsername(int uid, String username) {
        User user = getUser(uid);
        if (user == null) return false;
        users.remove(user);
        users.add(new CommonUser(username, user.getPassword(), user.getEmail(), uid));
        return true;
    }

    @Override
    public boolean changeEmail(int uid, String email) {
        User user = getUser(uid);
        if (user == null) return false;
        users.remove(user);
        users.add(new CommonUser(user.getName(), user.getPassword(), email, uid));
        return true;
    }

    @Override
    public User getUser(int uid) {
        for (CommonUser user : users) {
            if (user.getUserId() == uid) return user;
        }
        return null;
    }

    @Override
    public boolean deleteAccount(int userId) {
        User user = getUser(userId);
        if (user == null) return false;
        users.remove(getUser(userId));
        return true;
    }
}
