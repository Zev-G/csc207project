package view.pages;

import interface_adapter.ViewModel;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import interface_adapter.accountconfirm.AccountConfirmController;
import interface_adapter.accountdelete.AccountDeleteController;
import interface_adapter.accountlogout.AccountLogoutController;
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

    public AccountPage(App app) {
        super(app.getViewManager());
        this.viewModel = app.getAccountViewModel();
        this.accountConfirmController = app.getAccountConfirmController();
        this.accountLogoutController = app.getAccountLogoutController();
        this.accountDeleteController = app.getAccountDeleteController();

        setMargin(ViewConstants.MARGIN_M);
        pageTitle.setFontSize(ViewConstants.TEXT_LL);

        // Layout
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
        gbc.fill = GridBagConstraints.NONE; // Reset fill
        gbc.weightx = 0; // Reset weight
        gbc.anchor = GridBagConstraints.LINE_END; // Right-align label
        grid.add(emailLabel, gbc);

        // Email field
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow text field to expand
        gbc.weightx = 1.0; // Let the text field take extra space
        grid.add(emailField, gbc);

        buttons.setLayout(new FlowLayout());

        confirmButton.setPreferredSize(new Dimension(200, 80));
        cancelButton.setPreferredSize(new Dimension(200, 80));
        logoutButton.setPreferredSize(new Dimension(200, 80));
        deleteAccountButton.setPreferredSize(new Dimension(200, 80));

        // Add listeners
        confirmButton.addActionListener(this::confirmButtonPressed);
        cancelButton.addActionListener(this::cancelButtonPressed);
        logoutButton.addActionListener(this::logoutButtonPressed);
        deleteAccountButton.addActionListener(this::deleteAccountButtonPressed);
        statsButton.addActionListener(this::statsButtonPressed);

        loadCurrentState();
        viewModel.addPropertyChangeListener(evt -> loadCurrentState());
    }

    private void statsButtonPressed(ActionEvent event) {
        viewManager.navigate("stats");
    }

    private void deleteAccountButtonPressed(ActionEvent event) {
        accountDeleteController.pressed(viewModel.getState());
    }

    private void logoutButtonPressed(ActionEvent event) {
        accountLogoutController.logout();
    }

    private void cancelButtonPressed(ActionEvent event) {
        viewManager.back();
    }

    private void confirmButtonPressed(ActionEvent event) {
        accountConfirmController.pressed(viewModel.getState());
    }

    @Override
    public void loadState(AccountState state) {
        usernameField.setText(state.getUsername());
        emailField.setText(state.getEmail());
    }

    @Override
    public ViewModel<AccountState> getViewModel() {
        return viewModel;
    }
}
