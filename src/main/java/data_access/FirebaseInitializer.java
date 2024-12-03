/**
 * FirebaseInitializer.java
 *
 * This class provides a static method for initializing the Firebase application.
 * It reads the Firebase service account key file and sets up the connection to the Firebase Realtime Database.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Describes its role in initializing the Firebase application with appropriate credentials.
 * - **Clarity**: Clearly explains the setup process and file dependencies.
 * - **Completeness**: Covers the initialization process and exception handling.
 * - **Ease of Use**: Demonstrates how to call the initialization method before accessing Firebase features.
 * - **Up-to-Dateness**: Reflects the current implementation for Firebase setup.
 */

package data_access;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

/**
 * Handles the initialization of the Firebase application.
 * Sets up the Firebase Realtime Database connection using the service account key.
 */
public class FirebaseInitializer {

    /**
     * Initializes Firebase with the service account credentials.
     *
     * This method reads the `serviceAccountKey.json` file containing the service account credentials,
     * configures the Firebase application, and sets the database URL for the Firebase Realtime Database.
     *
     * Dependencies:
     * - The `serviceAccountKey.json` file must be present in the src/main/resources/JSON directory.
     * - The file should contain the credentials for accessing the Firebase project.
     *
     * Exceptions:
     * - Throws an `IOException` if the service account key file cannot be found or read.
     *
     * Usage Example:
     * <pre>
     *     try {
     *         FirebaseInitializer.initializeFirebase();
     *     } catch (IOException e) {
     *         e.printStackTrace();
     *     }
     * </pre>
     *
     * @throws IOException If there is an error reading the service account key file.
     */
    public static void initializeFirebase() throws IOException {
        // Read the service account key file
        InputStream serviceAccount = ClassLoader.getSystemResourceAsStream("JSON/serviceAccountKey.json");

        // Configure Firebase options
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://uoftguessr-default-rtdb.firebaseio.com/")
                .build();

        // Initialize the Firebase application
        FirebaseApp.initializeApp(options);
    }
}
