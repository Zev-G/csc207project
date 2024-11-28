package data_access;

import com.google.firebase.database.*;

import java.util.concurrent.CompletableFuture;

public class FirebaseUserDataAccess implements UserDataAccess {

    private final DatabaseReference database;

    public FirebaseUserDataAccess(DatabaseReference database) {

        this.database = database;

    }
}
