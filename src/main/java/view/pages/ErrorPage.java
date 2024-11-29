package view.pages;

import view.ViewConstants;
import view.components.AppViewManager;
import view.components.standard.DLabel;
import view.components.standard.DPanel;
import view.components.standard.RoundedButton;
import view.components.standard.VerticalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Page displayed when an error occurs in the application.
 */
public class ErrorPage extends Page {

    private final DLabel pageTitle = new DLabel("Oops, An Error Occurred.");
    private final VerticalPanel titleLayout = new VerticalPanel(pageTitle);

    private final JButton cancelButton = new RoundedButton("Return to main");
    private final DPanel buttons = new DPanel();
    private final DPanel grid = new DPanel();

    /**
     * Constructs an ErrorPage with the specified app view manager.
     *
     * @param app the application view manager
     */
    public ErrorPage(AppViewManager app) {
        super(app.getViewManager());

        configureComponents();
        setupLayout();
        setupListeners();
    }

    /**
     * Configures UI components.
     */
    private void configureComponents() {
        setMargin(ViewConstants.MARGIN_M);
        pageTitle.setFontSize(ViewConstants.TEXT_LL);

        grid.setMargin(0, 400, 0, 400);
        cancelButton.setPreferredSize(new Dimension(200, 80));
    }

    /**
     * Sets up the layout for the page.
     */
    private void setupLayout() {
        setLayout(new BorderLayout(0, 60));

        buttons.add(cancelButton);
        add(titleLayout, BorderLayout.PAGE_START);
        add(grid, BorderLayout.CENTER);
        add(buttons, BorderLayout.PAGE_END);

        setupGrid();
        buttons.setLayout(new FlowLayout());
    }

    /**
     * Configures the grid layout for displaying the title.
     */
    private void setupGrid() {
        grid.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Page title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        grid.add(pageTitle, gbc);
    }

    /**
     * Sets up listeners for the cancel button.
     */
    private void setupListeners() {
        cancelButton.addActionListener(this::cancelButtonPressed);
    }

    /**
     * Handles the cancel button press event.
     *
     * @param event the action event triggered by button press
     */
    private void cancelButtonPressed(ActionEvent event) {
        viewManager.navigate("main");
    }
}
