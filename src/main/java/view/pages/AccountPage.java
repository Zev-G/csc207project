package view.pages;

import interface_adapter.ViewModel;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import interface_adapter.accountconfirm.AccountConfirmController;
import interface_adapter.accountdelete.AccountDeleteController;
import interface_adapter.accountlogout.AccountLogoutController;
import view.View;
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
 * Represents the account page view in the application, displaying user account information and options to manage the account.
 */
public class AccountPage extends Page implements View<AccountState> {

    private final AccountViewModel viewModel;
    private final DLabel pageTitle = new DLabel("Account");
    private final VerticalPanel titleLayout = new VerticalPanel(pageTitle);
    private final DLabel usernameLabel = new DLabel("Username");
    private final DLabel emailLabel = new DLabel("Email");
    private final JTextField usernameField = new JTextField();
    private final JTextField emailField = new JTextField();
    private final JButton confirmButton = new RoundedButton("Confirm");
    private final JButton cancelButton = new RoundedButton("Cancel");
    private final JButton logoutButton = new RoundedButton("Logout");
    private final JButton deleteAccountButton = new RoundedButton("Delete Account");
    private final JButton statsButton = new RoundedButton("Stats");
    private final DPanel buttons = new DPanel();
    private final DPanel grid = new DPanel();
    private final AccountConfirmController accountConfirmController;
    private final AccountLogoutController accountLogoutController;
    private final AccountDeleteController accountDeleteController;

    /**
     * Constructs the AccountPage with the specified AppViewManager, initializing the UI and controllers.
     *
     * @param app the application view manager
     */
    public AccountPage(AppViewManager app) {
        super(app.getViewManager());
        this.viewModel = app.getAccountViewModel();
        this.accountConfirmController = app.getAccountConfirmController();
        this.accountLogoutController = app.getAccountLogoutController();
        this.accountDeleteController = app.getAccountDeleteController();

        setupUI();
        setupListeners();
        loadCurrentState();
        viewModel.addPropertyChangeListener(evt -> loadCurrentState());
    }

    /**
     * Sets up the UI layout and components.
     */
    private void setupUI() {
        setMargin(ViewConstants.MARGIN_M);
        pageTitle.setFontSize(ViewConstants.TEXT_LL);

        // Layout setup
        BorderLayout layout = new BorderLayout();
        layout.setVgap(60);
        setLayout(layout);
        buttons.add(confirmButton);
        buttons.add(cancelButton);
        buttons.add(logoutButton);
        buttons.add(deleteAccountButton);
        buttons.add(statsButton);
        add(titleLayout, BorderLayout.PAGE_START);
        add(grid, BorderLayout.CENTER);
        add(buttons, BorderLayout.PAGE_END);

        grid.setMargin(0, 400, 0, 400);
        grid.setLayout(new GridBagLayout());
        setupGridLayout();

        buttons.setLayout(new FlowLayout());
        Dimension buttonSize = new Dimension(200, 80);
        confirmButton.setPreferredSize(buttonSize);
        cancelButton.setPreferredSize(buttonSize);
        logoutButton.setPreferredSize(buttonSize);
        deleteAccountButton.setPreferredSize(buttonSize);
        statsButton.setPreferredSize(buttonSize);
    }

    /**
     * Configures the grid layout of the account details.
     */
    private void setupGridLayout() {
        GridBagConstraints gbc = new GridBagConstraints();

        // Page title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        grid.add(pageTitle, gbc);

        // Username label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        grid.add(usernameLabel, gbc);

        // Username field
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        grid.add(usernameField, gbc);

        // Email label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        grid.add(emailLabel, gbc);

        // Email field
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        grid.add(emailField, gbc);
    }

    /**
     * Sets up the action listeners for the buttons.
     */
    private void setupListeners() {
        confirmButton.addActionListener(this::confirmButtonPressed);
        cancelButton.addActionListener(this::cancelButtonPressed);
        logoutButton.addActionListener(this::logoutButtonPressed);
        deleteAccountButton.addActionListener(this::deleteAccountButtonPressed);
        statsButton.addActionListener(this::statsButtonPressed);
    }

    /**
     * Navigates to the stats page when the stats button is pressed.
     *
     * @param event the event triggered by pressing the stats button
     */
    private void statsButtonPressed(ActionEvent event) {
        viewManager.navigate("stats");
    }

    /**
     * Deletes the account when the delete account button is pressed.
     *
     * @param event the event triggered by pressing the delete account button
     */
    private void deleteAccountButtonPressed(ActionEvent event) {
        accountDeleteController.pressed(viewModel.getState());
    }


    /**
     * Logs out the user when the logout button is pressed.
     *
     * @param event the event triggered by pressing the logout button
     */
    private void logoutButtonPressed(ActionEvent event) {
        accountLogoutController.logout();
    }

    /**
     * Navigates back to the previous page when the cancel button is pressed.
     *
     * @param event the event triggered by pressing the cancel button
     */
    private void cancelButtonPressed(ActionEvent event) {
        viewManager.back();
    }

    /**
     * Confirms account changes when the confirm button is pressed.
     *
     * @param event the event triggered by pressing the confirm button
     */
    private void confirmButtonPressed(ActionEvent event) {
        viewModel.setState(new AccountState(true, usernameField.getText(),
                emailField.getText(), viewModel.getState().getPassword() ,viewModel.getState().getUserId()));
        accountConfirmController.pressed(viewModel.getState());
    }

    /**
     * Loads the account state into the view.
     *
     * @param state the account state to load
     */
    @Override
    public void loadState(AccountState state) {
        if (state != null) {
            usernameField.setText(state.getUsername());
            emailField.setText(state.getEmail());
        }
    }


    /**
     * Returns the view model associated with the account page.
     *
     * @return the account view model
     */
    @Override
    public ViewModel<AccountState> getViewModel() {
        return viewModel;
    }
}
