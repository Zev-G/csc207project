package data_access;

import entity.CommonUser;
import entity.PhotoLocation;
import entity.User;
import use_case.dataAccessInterface.LocationDataAccess;
import use_case.dataAccessInterface.UserDataAccess;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


/**
 * This is for testing, will not be used in production.
 */
public class DataAccessMock implements LocationDataAccess, UserDataAccess {

    private final ArrayList<PhotoLocation> locations = new ArrayList<>();

    private final ArrayList<CommonUser> users = new ArrayList<>();

    private Random random;

    /**
     * Constructs a DataAccessMock object with a specified seed for random number generation.
     * This constructor initializes the mock data access with predefined photo locations and a user.
     *
     * @param seed A long value used to seed the random number generator for consistent randomization.
     *             This allows for reproducible random behavior in tests.
     */
    public DataAccessMock(long seed) {
        // Locations
        random = new Random(seed);
        ImageIcon photo1 = new ImageIcon(ClassLoader.getSystemResource("photos/sample1.jpg"));
        PhotoLocation photoLocation1 = new PhotoLocation(photo1, new double[]{43.66742755781882, -79.39177102147445}, 1);
        ImageIcon photo2 = new ImageIcon(ClassLoader.getSystemResource("photos/sample2.jpeg"));
        PhotoLocation photoLocation2 = new PhotoLocation(photo2, new double[]{43.65984277958618, -79.39718377820866}, 2);
        locations.add(photoLocation1);
        locations.add(photoLocation2);
        // Users
        users.add(new CommonUser("Zev", "1234", "godfreyzev@gmail.com", "1"));
    }

    /**
     * Returns the user with the given ID.
     */
    public DataAccessMock() {
        this(new Random().nextLong());
    }

    /**
     * Returns the user with the given ID.
     * @return photo id
     */
    @Override
    public int getPhotoID() {
        return 0;
    }

    /**
     * Returns the user with the given ID.
     * @return photo location
     */
    @Override
    public PhotoLocation getPhotoLocationByID(int id) {
        return null;
    }

    /**
     * Deletes the user with the given ID.
     * @param seed seed for reproducibility
     */
    @Override
    public void setSeed(long seed) {
        random = new Random(seed);
    }

    /**
     * Returns a random photo location.
     * @return photo location
     */
    @Override
    public PhotoLocation getRandomLocation() {
        switch (random.nextInt(2)) {
            case 0:
                return locations.get(0);
            default:
                return locations.get(1);
        }
    }

    /**
     * Changes the username of the user with the given ID.
     * @param uid user id
     * @param username new username
     * @return true if successful, false otherwise
     */
    @Override
    public boolean changeUsername(String uid, String username) {
        User user = null;
        try {
            user = getUser(uid).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        if (user == null) return false;
        users.remove(user);
        users.add(new CommonUser(username, user.getPassword(), user.getEmail(), uid));
        return true;
    }

    /**
     * Changes the email of the user with the given ID.
     * @param uid user id
     * @param email new email
     * @return true if successful, false otherwise
     * */
    @Override
    public boolean changeEmail(String uid, String email) {
        User user = null;
        try {
            user = getUser(uid).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        if (user == null) return false;
        users.remove(user);
        users.add(new CommonUser(user.getName(), user.getPassword(), email, uid));
        return true;
    }

    /**
     * Returns the user with the given ID.
     *
     * @param uid user id
     * @return user
     */
    @Override
    public CompletableFuture<User> getUser(String uid) {
        for (CommonUser user : users) {
            if (Objects.equals(user.getUserId(), uid)) return CompletableFuture.completedFuture(user);
        }
        return null;
    }

    /**
     * Deletes the user with the given ID.
     * @param userId user id
     * @return true if successful, false otherwise
     */
    @Override
    public boolean deleteAccount(String userId) {
        User user = null;
        try {
            user = getUser(userId).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        if (user == null) return false;
        users.remove(getUser(userId));
        return true;
    }
}