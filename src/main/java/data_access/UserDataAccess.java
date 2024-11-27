package data_access;

import entity.CommonUser;
import entity.User;

public interface UserDataAccess {

    boolean changeUsername(int uid, String username);
    boolean changeEmail(int uid, String email);
    User getUser(int uid);

    boolean deleteAccount(int userId);
}
