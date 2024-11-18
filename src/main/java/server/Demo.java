package server;

import app.FirebaseInitializer;
import data_access.UserDataAccess;
import entity.CommonUser;

public class Demo {
    public static void main(String[] args) {
        try {
            // Step 1: Initialize Firebase
            FirebaseInitializer.initializeFirebase();

            // Step 2: Create UserDataAccess instance
            UserDataAccess userDataAccess = new UserDataAccess();

            // Step 3: Create a demonstration user
            // User details
            String email = "demo@example.com";
            String password = "securepassword";
            CommonUser demoUser = new CommonUser("DemoUser", password);

            // Step 4: Add the user to Firebase
            userDataAccess.addUser(demoUser, email, password);

            System.out.println("Demonstration user added to Firebase!");
        } catch (Exception e) {
            System.err.println("Error adding demonstration user: " + e.getMessage());
        }
    }
}
