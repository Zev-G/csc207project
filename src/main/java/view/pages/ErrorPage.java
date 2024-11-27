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

public class ErrorPage extends Page {

    private final DLabel pageTitle = new DLabel("Oops, An Error Occurred.");
    private final VerticalPanel titleLayout = new VerticalPanel(pageTitle);

    private final JButton cancelButton = new RoundedButton("Return to main");
    private final DPanel buttons = new DPanel();
    private final DPanel grid = new DPanel();

    public ErrorPage(App app) {
        super(app.getViewManager());


        setMargin(ViewConstants.MARGIN_M);
        pageTitle.setFontSize(ViewConstants.TEXT_LL);

        // Layout
        BorderLayout layout = new BorderLayout();
        layout.setVgap(60);
        setLayout(layout);
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


        buttons.setLayout(new FlowLayout());

        cancelButton.setPreferredSize(new Dimension(200, 80));

        // Add listeners
        cancelButton.addActionListener(this::cancelButtonPressed);

    }

    private void cancelButtonPressed(ActionEvent event) {
        viewManager.navigate("main");
    }
}
