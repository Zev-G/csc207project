package view.pages;

import interface_adapter.image.ImagePageController;
import interface_adapter.image.ImagePageViewModel;
import view.components.AppViewManager;
import view.components.game.InteractiveMap;
import view.utils.ImageScaler;
import view.components.standard.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * This class represents a user interface page for uploading images
 * and selecting coordinates on an interactive map. It integrates with the
 * {@code ImagePageController} to handle the business logic and uses the
 * {@code ImagePageViewModel} to manage the view state.
 */
public class ImagePage extends Page {

    private final ImagePageController controller;
    private final ImagePageViewModel viewModel;

    private final JLabel instructionsLabel = new JLabel("Upload an Image:");
    private final RoundedButton uploadButton = new RoundedButton("Choose Image");
    private final JLabel selectedFileLabel = new JLabel("No file selected");
    private final RoundedButton backButton = new RoundedButton("BACK");
    private final RoundedButton uploadToImgurButton = new RoundedButton("Upload Image");

    private final InteractiveMap interactiveMap; // Map for selecting coordinates
    private File selectedFile;

    /**
     * Constructs an {@code ImagePage} instance with the specified app manager, controller,
     * and view model.
     *
     * @param app       the {@code AppViewManager} responsible for managing navigation and views.
     * @param controller the {@code ImagePageController} to handle image upload logic.
     * @param viewModel  the {@code ImagePageViewModel} to manage the UI state.
     */
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
        uploadButton.setPreferredSize(new Dimension(200, 50)); // Adjusted button size
        uploadButton.setFont(new Font("Arial", Font.BOLD, 16)); // Bold text
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
        backButton.setPreferredSize(new Dimension(200, 50)); // Adjusted button size
        backButton.setFont(new Font("Arial", Font.BOLD, 16)); // Bold text
        uploadToImgurButton.setPreferredSize(new Dimension(200, 50)); // Adjusted button size
        uploadToImgurButton.setFont(new Font("Arial", Font.BOLD, 16)); // Bold text
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

    /**
     * Opens a file chooser dialog to allow the user to select an image file.
     * Updates the {@code selectedFileLabel} to display the name of the chosen file
     * or indicates no file was selected.
     */
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

    /**
     * Handles the upload process by validating the selected file and coordinates,
     * then delegates the image upload to the controller.
     * Displays appropriate messages for success or failure.
     */
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
