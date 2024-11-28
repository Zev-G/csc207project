package view.pages;

import okhttp3.*;
import view.ViewConstants;
import view.app.App;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImagePage extends Page {

    private static final String IMGUR_API_URL = "https://api.imgur.com/3/image";
    private static final String ACCESS_TOKEN = "50ebc9d32abce50f92c2794ae7b36aa3e743b272";

    private final JLabel instructionsLabel = new JLabel("Upload an Image:");
    private final JButton uploadButton = new JButton("Choose Image");
    private final JLabel selectedFileLabel = new JLabel("No file selected");
    private final JButton backButton = new JButton("BACK");
    private final JButton uploadToImgurButton = new JButton("Upload Image");

    private final JTextField latitudeField = new JTextField(15);
    private final JTextField longitudeField = new JTextField(15);

    private File selectedFile;

    public ImagePage(App app) {
        super(app.getViewManager());

        // Configure layout
        setLayout(new BorderLayout(20, 20));
        setMargin(ViewConstants.MARGIN_M);

        // Header Panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        instructionsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(instructionsLabel);

        // File Selection Panel
        JPanel fileSelectionPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        uploadButton.setPreferredSize(new Dimension(200, 40));
        uploadButton.setFont(new Font("Arial", Font.BOLD, 16));
        selectedFileLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        fileSelectionPanel.add(uploadButton);
        fileSelectionPanel.add(selectedFileLabel);

        // Coordinates Input Panel
        JPanel coordinatesPanel = new JPanel(new GridBagLayout());
        coordinatesPanel.setBorder(BorderFactory.createTitledBorder("Add Coordinates (Optional)"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        coordinatesPanel.add(new JLabel("Latitude:"), gbc);

        gbc.gridx = 1;
        latitudeField.setPreferredSize(new Dimension(150, 30));
        coordinatesPanel.add(latitudeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        coordinatesPanel.add(new JLabel("Longitude:"), gbc);

        gbc.gridx = 1;
        longitudeField.setPreferredSize(new Dimension(150, 30));
        coordinatesPanel.add(longitudeField, gbc);

        // Upload Panel
        JPanel uploadPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        uploadToImgurButton.setPreferredSize(new Dimension(250, 50));
        uploadToImgurButton.setFont(new Font("Arial", Font.BOLD, 18));
        uploadPanel.add(uploadToImgurButton);

        // Back Button Panel
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButton.setPreferredSize(new Dimension(200, 40));
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backPanel.add(backButton);

        // Add components to main layout
        add(headerPanel, BorderLayout.PAGE_START);
        add(fileSelectionPanel, BorderLayout.CENTER);
        add(coordinatesPanel, BorderLayout.SOUTH);
        add(uploadPanel, BorderLayout.PAGE_END);
        add(backPanel, BorderLayout.WEST);

        // Add listeners
        uploadButton.addActionListener(event -> chooseFile());
        backButton.addActionListener(event -> viewManager.navigate("main"));
        uploadToImgurButton.addActionListener(event -> {
            try {
                uploadImageToImgur();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to upload image: " + e.getMessage());
            }
        });
    }

    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            selectedFileLabel.setText("Selected File: " + selectedFile.getName());
        } else {
            selectedFileLabel.setText("No file selected");
        }
    }

    private void uploadImageToImgur() throws IOException {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Please select an image first.");
            return;
        }

        String latitude = latitudeField.getText().trim();
        String longitude = longitudeField.getText().trim();

        String description = latitude.isEmpty() || longitude.isEmpty()
                ? "Uploaded via ImagePage."
                : "Coordinates: [" + latitude + ", " + longitude + "]";

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", selectedFile.getName(),
                        RequestBody.create(selectedFile, MediaType.parse("image/jpeg")))
                .addFormDataPart("description", description)
                .build();

        Request request = new Request.Builder()
                .url(IMGUR_API_URL)
                .addHeader("Authorization", "Bearer " + ACCESS_TOKEN)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                JOptionPane.showMessageDialog(this, "Image uploaded successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to upload image. Response: " + response.body().string());
            }
        }
    }
}
