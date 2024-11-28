package view.pages;

import okhttp3.*;
import view.ViewConstants;
import view.app.App;
import view.components.standard.DPanel;
import view.components.standard.VerticalPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImagePage extends Page {

    private static final String IMGUR_API_URL = "https://api.imgur.com/3/image";
    private static final String ACCESS_TOKEN = "50ebc9d32abce50f92c2794ae7b36aa3e743b272";

    private final JLabel instructionsLabel = new JLabel("Upload an image file:");
    private final JButton uploadButton = new JButton("Choose File");
    private final JLabel selectedFileLabel = new JLabel("No file selected");
    private final JButton backButton = new JButton("Back");
    private final JButton uploadToImgurButton = new JButton("Upload to Imgur");
    private File selectedFile;

    public ImagePage(App app) {
        super(app.getViewManager());

        // Configure layout
        setLayout(new BorderLayout());
        setMargin(ViewConstants.MARGIN_M);

        // Instructions panel
        DPanel instructionsPanel = new VerticalPanel(instructionsLabel);
        instructionsLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        instructionsPanel.setAlignmentX(CENTER_ALIGNMENT);

        // File upload panel
        DPanel uploadPanel = new VerticalPanel();
        uploadPanel.add(uploadButton);
        uploadPanel.add(selectedFileLabel);
        uploadPanel.add(uploadToImgurButton);

        // Back button panel
        DPanel backPanel = new DPanel();
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(backButton);

        // Add components
        add(instructionsPanel, BorderLayout.PAGE_START);
        add(uploadPanel, BorderLayout.CENTER);
        add(backPanel, BorderLayout.PAGE_END);

        // Add listeners
        uploadButton.addActionListener(event -> chooseFile());
        backButton.addActionListener(event -> viewManager.navigate("main")); // Navigate back to MainPage
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

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", selectedFile.getName(),
                        RequestBody.create(selectedFile, MediaType.parse("image/jpeg")))
                .build();

        Request request = new Request.Builder()
                .url(IMGUR_API_URL)
                .addHeader("Authorization", "Bearer " + ACCESS_TOKEN)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JOptionPane.showMessageDialog(this, "Image uploaded successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to upload image. Response: " + response.body().string());
            }
        }
    }
}
