package data_access;

import com.google.firebase.database.DatabaseReference;
import use_case.signup.SignUpInputData;

public class FirebaseSignUpDataAccess implements SignUpDataAccess {

    private final DatabaseReference database;

    public FirebaseSignUpDataAccess(DatabaseReference database) {
        this.database = database;
    }
}
