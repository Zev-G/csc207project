package data_access;

import entity.CommonUser;
import entity.PhotoLocation;
import entity.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;


/**
 * This is for testing, will not be used in production.
 */
public class DataAccessMock implements LocationDataAccess, UserDataAccess {

    private final ArrayList<PhotoLocation> locations = new ArrayList<>();

    private final ArrayList<CommonUser> users = new ArrayList<>();

    private Random random;

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
        users.add(new CommonUser("Zev", "1234", "godfreyzev@gmail.com", 1));
    }

    public DataAccessMock() {
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
        switch (random.nextInt(2)) {
            case 0:
                return locations.get(0);
            default:
                return locations.get(1);
        }
    }

    @Override
    public boolean changeUsername(int uid, String username) {
        User user = getUser(uid);
        if (user == null) return false;
        users.remove(user);
        users.add(new CommonUser(username, user.getPassword(),user.getEmail(), uid));
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
