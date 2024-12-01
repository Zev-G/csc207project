package view.pages;

import interface_adapter.multiplayer.MultiplayerController;
import interface_adapter.multiplayer.MultiplayerViewModel;
import view.ViewConstants;
import view.components.AppViewManager;
import view.components.standard.DLabel;
import view.components.standard.DPanel;
import view.components.standard.RoundedButton;
import view.components.standard.VerticalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Represents the multiplayer page of the application where users can connect with other players.
 */
public class MultiplayerPage extends Page {

    /** View model for managing multiplayer state. */
    private final MultiplayerViewModel viewModel;

    /** Label displaying the page title. */
    private final DLabel pageTitle = new DLabel("Multiplayer Game");

    /** Layout container for the title. */
    private final VerticalPanel titleLayout = new VerticalPanel(pageTitle);

    /** Label for the user's username input field. */
    private final DLabel usernameLabel = new DLabel("Username");

    /** Label for the opponent's username input field. */
    private final DLabel theirUsername = new DLabel("Their username");

    /** Input field for the user's username. */
    private final JTextField usernameField = new JTextField();

    /** Input field for the opponent's username. */
    private final JTextField theirUsernameField = new JTextField();

    /** Button for initiating a connection to an opponent. */
    private final JButton connect = new RoundedButton("Connect");

    /** Button for canceling the connection process. */
    private final JButton cancelButton = new RoundedButton("Cancel");

    /** Panel for holding buttons. */
    private final DPanel buttons = new DPanel();

    /** Panel for the main grid layout of the page. */
    private final DPanel grid = new DPanel();

    /** Controller for handling multiplayer logic. */
    private final MultiplayerController controller;

    /**
     * Constructs a new MultiplayerPage.
     *
     * @param app        The app view manager for managing navigation and view models.
     * @param controller The multiplayer controller for handling connection logic.
     */
    public MultiplayerPage(AppViewManager app, MultiplayerController controller) {
        super(app.getViewManager());

        this.viewModel = app.getMultiplayerViewModel();
        this.controller = controller;

        // Configure margins and styles
        setMargin(ViewConstants.MARGIN_M);
        pageTitle.setFontSize(ViewConstants.TEXT_LL);

        // Configure layout
        BorderLayout layout = new BorderLayout();
        layout.setVgap(60);
        setLayout(layout);
        buttons.add(connect);
        buttons.add(cancelButton);
        add(titleLayout, BorderLayout.PAGE_START);
        add(grid, BorderLayout.CENTER);
        add(buttons, BorderLayout.PAGE_END);

        // Configure grid layout and constraints
        grid.setMargin(0, 400, 0, 400);
        grid.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Page title configuration
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        grid.add(pageTitle, gbc);

        // Username label configuration
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        grid.add(usernameLabel, gbc);

        // Username field configuration
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        grid.add(usernameField, gbc);

        // Opponent username label configuration
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        grid.add(theirUsername, gbc);

        // Opponent username field configuration
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        grid.add(theirUsernameField, gbc);

        // Configure buttons
        buttons.setLayout(new FlowLayout());
        connect.setPreferredSize(new Dimension(200, 80));
        cancelButton.setPreferredSize(new Dimension(200, 80));

        // Add action listeners
        connect.addActionListener(this::connectButtonPressed);
        cancelButton.addActionListener(this::cancelButtonPressed);

        // Add property change listener to update the connect button text based on state
        viewModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateConnectButtonText(viewModel.getState());
            }
        });
    }

    /**
     * Handles the connect button press event, validating input and initiating a connection.
     *
     * @param event The action event triggered by the button press.
     */
    private void connectButtonPressed(ActionEvent event) {
        if (usernameField.getText().isEmpty() || theirUsernameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username");
        }
        else {
            viewModel.setState("wait");
            controller.execute(usernameField.getText(), theirUsernameField.getText());
        }
    }

    /**
     * Handles the cancel button press event, navigating back if not in a "waiting" state.
     *
     * @param event The action event triggered by the button press.
     */
    private void cancelButtonPressed(ActionEvent event) {
        if (!viewModel.getState().equals("wait")) {
            viewManager.back();
        }
    }

    /**
     * Initializes the page by resetting the multiplayer state to "connect".
     */
    @Override
    public void init() {
        viewModel.setState("connect");
    }

    /**
     * Updates the connect button text based on the current multiplayer state.
     *
     * @param state The current state of the multiplayer view model.
     */
    private void updateConnectButtonText(String state) {
        switch (state) {
            case "connect":
                connect.setText("Connect");
                break;
            case "wait":
                connect.setText("Waiting...");
                break;
            case "error":
                connect.setText("Error Occurred, retry");
                break;
            case "timeout":
                connect.setText("Timeout, retry");
                break;
            default:
                connect.setText("Connect");
                break;
        }
    }
}
