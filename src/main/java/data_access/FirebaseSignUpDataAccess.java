package data_access;

import com.google.firebase.database.DatabaseReference;
import use_case.signup.SignUpInputData;

public class FirebaseSignUpDataAccess implements SignUpDataAccess {

    private final DatabaseReference database;

    public FirebaseSignUpDataAccess(DatabaseReference database) {
        this.database = database;
    }

    @Override
    public void createUser(SignUpInputData data) {
        database.child("users").child(data.getUsername())
                .setValueAsync(data)
                .addOnSuccessListener(unused -> System.out.println("User created successfully!"))
                .addOnFailureListener(e -> System.err.println("Error creating user:" + e.getMessage()));
    }
}
:;