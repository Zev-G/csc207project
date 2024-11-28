package view.pages;

import view.ViewConstants;
import view.app.App;
import view.components.standard.DPanel;
import view.components.standard.VerticalPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ImagePage extends Page {

    private final JLabel instructionsLabel = new JLabel("Upload an image file:");
    private final JButton uploadButton = new JButton("Choose File");
    private final JLabel selectedFileLabel = new JLabel("No file selected");
    private final JButton backButton = new JButton("Back");

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
    }

    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectedFileLabel.setText("Selected File: " + selectedFile.getName());
        } else {
            selectedFileLabel.setText("No file selected");
        }
    }
}
