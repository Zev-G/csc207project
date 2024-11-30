package data_access;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

/**
 * Initializing Firebase.
 */
public class FirebaseInitializer {

    /**
     * Initializing.
     *
     * @throws IOException exception.
     */
    public static void initializeFirebase() throws IOException {
        InputStream serviceAccount = ClassLoader.getSystemResourceAsStream("JSON/serviceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://uoftguessr-default-rtdb.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp(options);
    }
}