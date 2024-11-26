package view.pages;

import interface_adapter.multiplayer.MultiplayerController;
import interface_adapter.multiplayer.MultiplayerViewModel;
import view.ViewConstants;
import view.app.App;
import view.components.standard.DLabel;
import view.components.standard.DPanel;
import view.components.standard.RoundedButton;
import view.components.standard.VerticalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MultiplayerPage extends Page {

    private final MultiplayerViewModel viewModel;

    private final DLabel pageTitle = new DLabel("Multiplayer Game");
    private final VerticalPanel titleLayout = new VerticalPanel(pageTitle);
    private final DLabel usernameLabel = new DLabel("Username");
    private final DLabel theirUsername = new DLabel("Their username");
    private final JTextField usernameField = new JTextField();
    private final JTextField theirUsernameField = new JTextField();

    private final JButton connect = new RoundedButton("Connect");
    private final DPanel buttons = new DPanel();
    private final DPanel grid = new DPanel();

    private final MultiplayerController controller;

    public MultiplayerPage(App app, MultiplayerController controller) {
        super(app.getViewManager());

        viewModel = app.getMultiplayerViewModel();

        this.controller = controller;

        setMargin(ViewConstants.MARGIN_M);
        pageTitle.setFontSize(ViewConstants.TEXT_LL);

        // Layout
        BorderLayout layout = new BorderLayout();
        layout.setVgap(60);
        setLayout(layout);
        buttons.add(connect);
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

        // Opponent username
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE; // Reset fill
        gbc.weightx = 0; // Reset weight
        gbc.anchor = GridBagConstraints.LINE_END; // Right-align label
        grid.add(theirUsername, gbc);

        // Opponent username field
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow text field to expand
        gbc.weightx = 1.0; // Let the text field take extra space
        grid.add(theirUsernameField, gbc);

        buttons.setLayout(new FlowLayout());

        connect.setPreferredSize(new Dimension(200, 80));

        // Add listeners
        connect.addActionListener(this::connectButtonPressed);
        viewModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String state = viewModel.getState();
                switch (state) {
                    case "connect":
                        connect.setText("Connect");
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
        });

    }

    private void connectButtonPressed(ActionEvent event) {
        if (usernameField.getText().isEmpty() || theirUsernameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username");
        } else {
            controller.execute(usernameField.getText(), theirUsernameField.getText());
        }

    }

    @Override
    public void init() {
        viewModel.setState("connect");
    }
}
