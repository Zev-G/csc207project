package view.pages;

import interface_adapter.ViewModel;
import interface_adapter.signup.SignUpController;
import interface_adapter.signup.SignUpState;
import interface_adapter.signup.SignUpViewModel;
import view.View;
import view.ViewConstants;
import app.App;
import view.components.AppViewManager;
import view.components.standard.DLabel;
import view.components.standard.DPanel;
import view.components.standard.RoundedButton;
import view.components.standard.VerticalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *  This class represents the sign-up page of the application. It allows users to enter their username, email, and password to create a new account.
 */
public class SignUpPage extends Page implements View<SignUpState> {

    private final SignUpViewModel viewModel;
    private final AppViewManager app;
    private final SignUpController controller;

    // UI Components.
    private final DLabel pageTitle = new DLabel("Sign Up");
    private final VerticalPanel titleLayout = new VerticalPanel(pageTitle);
    private final DLabel usernameLabel = new DLabel("Username");
    private final DLabel emailLabel = new DLabel("Email");
    private final DLabel passwordLabel = new DLabel("Password");
    private final JTextField usernameField = new JTextField();
    private final JTextField emailField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();

    private final JButton signUpButton = new RoundedButton("Sign Up");
    private final JButton cancelButton = new RoundedButton("Cancel");
    private final DPanel buttons = new DPanel();
    private final DPanel grid = new DPanel();

    public SignUpPage(AppViewManager app, SignUpController signUpController, SignUpViewModel signUpViewModel) {

        super(app.getViewManager());
        this.app = app;
        this.controller = signUpController;
        this.viewModel = signUpViewModel;

        setMargin(ViewConstants.MARGIN_M);
        pageTitle.setFontSize(ViewConstants.TEXT_LL);

        // Layout
        BorderLayout layout = new BorderLayout();
        layout.setVgap(60);
        setLayout(layout);
        buttons.add(signUpButton);
        buttons.add(cancelButton);
        add(titleLayout, BorderLayout.PAGE_START);
        add(grid, BorderLayout.CENTER);
        add(buttons, BorderLayout.PAGE_END);

        grid.setMargin(0, 400, 0, 400);

        grid.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Page title
        gbc.gridx = 0;
        gbc.gridy = 0;
        // Span across two columns
        gbc.gridwidth = 2;
        // Padding around the component
        gbc.insets = new Insets(10, 10, 10, 10);
        // Center alignment
        gbc.anchor = GridBagConstraints.CENTER;
        grid.add(pageTitle, gbc);

        // Username label
        gbc.gridx = 0;
        gbc.gridy = 1;
        // Single column
        gbc.gridwidth = 1;
        // Left-align label
        gbc.anchor = GridBagConstraints.LINE_START;
        grid.add(usernameLabel, gbc);

        // Username field
        gbc.gridx = 1;
        gbc.gridy = 1;
        // Allow text field to expand
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Let the text field take extra space
        gbc.weightx = 1.0;
        grid.add(usernameField, gbc);

        // Email label
        gbc.gridx = 0;
        gbc.gridy = 2;
        // Right-align label
        gbc.anchor = GridBagConstraints.LINE_END;
        grid.add(emailLabel, gbc);

        // Email field
        gbc.gridx = 1;
        gbc.gridy = 2;
        // Allow text field to expand
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Let the text field take extra space
        gbc.weightx = 1.0;
        grid.add(emailField, gbc);

        // Password label
        gbc.gridx = 0;
        gbc.gridy = 3;
        // Right-align label
        gbc.anchor = GridBagConstraints.LINE_END;
        grid.add(passwordLabel, gbc);

        // Password field
        gbc.gridx = 1;
        gbc.gridy = 3;
        // Allow text field to expand
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Let the text field take extra space
        gbc.weightx = 1.0;
        grid.add(passwordField, gbc);

        buttons.setLayout(new FlowLayout());

        signUpButton.setPreferredSize(new Dimension(200, 80));
        cancelButton.setPreferredSize(new Dimension(200, 80));

        // Add listeners
        signUpButton.addActionListener(this::signUpButtonPressed);
        cancelButton.addActionListener(this::cancelButtonPressed);

        loadCurrentState();
        viewModel.addPropertyChangeListener(evt -> loadCurrentState());
    }

    /**
     * Handles the click event on the "Sign Up" button. Retrieves user inputs and calls the controller's handleSignUp method.
     * @param event The ActionEvent triggered by the button click.
     */
    private void signUpButtonPressed(ActionEvent event) {
        // Retrieve user inputs
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        app.getSignUpController().handleSignUp(username, email, password);
    }

    /**
     * Handles the click event on the "Cancel" button. Navigates back to the previous page.
     * @param event The ActionEvent triggered by the button click.
     */
    private void cancelButtonPressed(ActionEvent event) {
        // Return to the previous page
        viewManager.back();
    }

    /**
     * Updates the UI based on the current state of the sign-up process.
     * @param state The current SignUpState object.
     */
    @Override
    public void loadState(SignUpState state) {

        if (state == null) {
            return;
        }
        if (state.isSigningUp()) {
            // Disable inputs and show a progress indicator
            signUpButton.setEnabled(false);
            cancelButton.setEnabled(false);
        }
        else {
            // Enable inputs
            signUpButton.setEnabled(true);
            cancelButton.setEnabled(true);

            if (state.getErrorMessage() != null) {
                // Show error message
                JOptionPane.showMessageDialog(this, state.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Reset fields if sign-up was successful
        if (!state.isSigningUp() && state.getErrorMessage() == null) {
            usernameField.setText("");
            emailField.setText("");
            passwordField.setText("");
        }
    }

    /**
     * Returns the view model associated with this page.
     * @return The SignUpViewModel object.
     */
    @Override
    public ViewModel<SignUpState> getViewModel() {
        return viewModel;
    }
}
