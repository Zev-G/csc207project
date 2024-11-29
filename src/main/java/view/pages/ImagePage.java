package view.pages;

import interface_adapter.image.ImagePageController;
import interface_adapter.image.ImagePageViewModel;
import view.components.AppViewManager;
import view.components.game.InteractiveMap;
import view.utils.ImageScaler;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * ImagePage handles the UI for image uploads and integrates with the ImagePageController.
 */
public class ImagePage extends Page {

    private final ImagePageController controller;
    private final ImagePageViewModel viewModel;

    private final JLabel instructionsLabel = new JLabel("Upload an Image:");
    private final JButton uploadButton = new JButton("Choose Image");
    private final JLabel selectedFileLabel = new JLabel("No file selected");
    private final JButton backButton = new JButton("BACK");
    private final JButton uploadToImgurButton = new JButton("Upload Image");

    private final InteractiveMap interactiveMap; // Map for selecting coordinates
    private File selectedFile;

    public ImagePage(AppViewManager app, ImagePageController controller, ImagePageViewModel viewModel) {
        super(app.getViewManager());
        this.controller = controller;
        this.viewModel = viewModel;

        // Configure layout
        setLayout(new BorderLayout(10, 10));
        setMargin(20);

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

        int mapDimension = 400;
        ImageIcon rawMap = new ImageIcon(ClassLoader.getSystemResource("photos/UofTmap.jpg"));
        ImageIcon scaledMap = ImageScaler.getScaledImageIcon(rawMap, mapDimension, mapDimension);
        interactiveMap = new InteractiveMap(scaledMap, new double[]{43.669978, 43.657185, -79.403269, -79.384892});
        interactiveMap.setPreferredSize(new Dimension(mapDimension, mapDimension));
        interactiveMap.setBorder(BorderFactory.createTitledBorder("Select Location on Map"));

        // Map Panel
        JPanel mapPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mapPanel.add(interactiveMap);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        backButton.setPreferredSize(new Dimension(150, 30));
        uploadToImgurButton.setPreferredSize(new Dimension(150, 30));
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        uploadToImgurButton.setFont(new Font("Arial", Font.PLAIN, 14));
        buttonsPanel.add(backButton);
        buttonsPanel.add(uploadToImgurButton);

        // Main Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.add(fileSelectionPanel, BorderLayout.NORTH);
        contentPanel.add(mapPanel, BorderLayout.CENTER);

        // Add components to main layout
        add(headerPanel, BorderLayout.PAGE_START);
        add(contentPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.PAGE_END);

        // Add listeners
        uploadButton.addActionListener(event -> chooseFile());
        backButton.addActionListener(event -> viewManager.navigate("main"));
        uploadToImgurButton.addActionListener(event -> handleUpload());
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

    private void handleUpload() {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Please select an image first.");
            return;
        }

        double[] chosenCoord = interactiveMap.getChosenCoord();
        if (chosenCoord[0] == 0 && chosenCoord[1] == 0) {
            JOptionPane.showMessageDialog(this, "Please select coordinates on the map.");
            return;
        }

        String description = "Coordinates: [" + chosenCoord[0] + ", " + chosenCoord[1] + "]";
        try {
            controller.uploadImage(selectedFile, description);
            JOptionPane.showMessageDialog(this, "Image uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to upload image: " + e.getMessage());
        }
    }
}
