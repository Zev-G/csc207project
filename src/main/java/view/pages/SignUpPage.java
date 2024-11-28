package view.pages;

import interface_adapter.ViewModel;
import interface_adapter.signup.SignUpState;
import interface_adapter.signup.SignUpViewModel;
import view.View;
import view.ViewConstants;
import view.app.App;
import view.components.standard.DLabel;
import view.components.standard.DPanel;
import view.components.standard.RoundedButton;
import view.components.standard.VerticalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SignUpPage extends Page implements View<SignUpState> {

    private final SignUpViewModel viewModel;

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

    public SignUpPage(App app) {
        super(app.getViewManager());
        this.viewModel = app.getSignUpViewModel();

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
        gbc.gridwidth = 2; // Span across two columns
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around the component
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
        grid.add(pageTitle, gbc);

        // Username label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Single column
        gbc.anchor = GridBagConstraints.LINE_END; // Right-align label
        grid.add(usernameLabel, gbc);

        // Username field
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow text field to expand
        gbc.weightx = 1.0; // Let the text field take extra space
        grid.add(usernameField, gbc);

        // Email label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END; // Right-align label
        grid.add(emailLabel, gbc);

        // Email field
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow text field to expand
        gbc.weightx = 1.0; // Let the text field take extra space
        grid.add(emailField, gbc);

        // Password label
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END; // Right-align label
        grid.add(passwordLabel, gbc);

        // Password field
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow text field to expand
        gbc.weightx = 1.0; // Let the text field take extra space
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

    private void signUpButtonPressed(ActionEvent event) {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        // Set credentials in the view model
        viewModel.setCredentials(username, email, password);

        // Initiate sign-up process
        viewModel.signUp();
    }

    private void cancelButtonPressed(ActionEvent event) {
        // Return to the previous page
        viewManager.back();
    }

    @Override
    public void loadState(SignUpState state) {
        if (state.isSigningUp()) {
            // Disable inputs and show a progress indicator
            signUpButton.setEnabled(false);
            cancelButton.setEnabled(false);
        } else {
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

    @Override
    public ViewModel<SignUpState> getViewModel() {
        return viewModel;
    }
}
