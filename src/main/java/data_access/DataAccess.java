package data_access;

import entity.PhotoLocation;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/**
 * Actual implementation of data access that fetches images and their corresponding locations.
 */
public class DataAccess implements LocationDataAccess {

    private static final String IMGUR_API_URL = "https://api.imgur.com/3/account/me/images";
    private static final String ACCESS_TOKEN = "50ebc9d32abce50f92c2794ae7b36aa3e743b272";

    private final ArrayList<PhotoLocation> locations = new ArrayList<>();
    private Random random;

    public DataAccess(long seed) {
        random = new Random(seed);
//        fetchImagesAndLocations();
    }

    public DataAccess() {
        this(new Random().nextLong());
    }

    @Override
    public int getPhotoID() {
        return 0;
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
}
