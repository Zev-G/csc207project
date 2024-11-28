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

    private final JTextField coordinateField = new JTextField(20); // Singular coordinate input field

    private File selectedFile;

    public ImagePage(App app) {
        super(app.getViewManager());

        // Configure layout
        setLayout(new BorderLayout(10, 10));
        setMargin(ViewConstants.MARGIN_M);

        // Header Panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        instructionsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(instructionsLabel);

        // File Selection Panel
        JPanel fileSelectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fileSelectionPanel.setBorder(BorderFactory.createTitledBorder("Select Image"));
        uploadButton.setPreferredSize(new Dimension(150, 30));
        uploadButton.setFont(new Font("Arial", Font.PLAIN, 14));
        selectedFileLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        fileSelectionPanel.add(uploadButton);
        fileSelectionPanel.add(selectedFileLabel);

        // Coordinate Input Panel
        JPanel coordinatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        coordinatePanel.setBorder(BorderFactory.createTitledBorder("Add Coordinate (Optional)"));
        JLabel coordinateLabel = new JLabel("Coordinate:");
        coordinateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        coordinateField.setPreferredSize(new Dimension(200, 30));
        coordinatePanel.add(coordinateLabel);
        coordinatePanel.add(coordinateField);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        backButton.setPreferredSize(new Dimension(150, 30));
        uploadToImgurButton.setPreferredSize(new Dimension(150, 30));
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        uploadToImgurButton.setFont(new Font("Arial", Font.PLAIN, 14));
        buttonsPanel.add(backButton);
        buttonsPanel.add(uploadToImgurButton);

        // Main Content Panel
        JPanel contentPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        contentPanel.add(fileSelectionPanel);
        contentPanel.add(coordinatePanel);

        // Add components to main layout
        add(headerPanel, BorderLayout.PAGE_START);
        add(contentPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.PAGE_END);

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
            selectedFileLabel.setText(selectedFile.getName());
        } else {
            selectedFileLabel.setText("No file selected");
        }
    }

    private void uploadImageToImgur() throws IOException {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Please select an image first.");
            return;
        }

        String coordinate = coordinateField.getText().trim();

        String description = coordinate.isEmpty()
                ? "Uploaded via ImagePage."
                : "Coordinate: " + coordinate;

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
