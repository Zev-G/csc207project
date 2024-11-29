package view.pages;

import interface_adapter.mgame.MGameEndViewModel;
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

public class EndMultiplayerGamePage extends Page {

    private final MGameEndViewModel viewModel;

    private final DLabel pageTitle = new DLabel("");
    private final VerticalPanel titleLayout = new VerticalPanel(pageTitle);
    private final DLabel yourScore = new DLabel("You got: 0");
    private final DLabel theirScore = new DLabel("They got: 0");

    private final JButton cancelButton = new RoundedButton("Return to main");
    private final DPanel buttons = new DPanel();
    private final DPanel grid = new DPanel();

    public EndMultiplayerGamePage(AppViewManager app) {
        super(app.getViewManager());

        viewModel = app.getmGameEndViewModel();

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

        // Username label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Single column
        gbc.anchor = GridBagConstraints.CENTER; // Right-align label
        grid.add(yourScore, gbc);

        // Opponent username
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE; // Reset fill
        gbc.weightx = 0; // Reset weight
        gbc.anchor = GridBagConstraints.CENTER; // Right-align label
        grid.add(theirScore, gbc);


        buttons.setLayout(new FlowLayout());

        cancelButton.setPreferredSize(new Dimension(200, 80));

        // Add listeners
        cancelButton.addActionListener(this::cancelButtonPressed);

        viewModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (viewModel.getState().getUser() > viewModel.getState().getOpp()) {
                    pageTitle.setText("You WIN!");
                } else if (viewModel.getState().getUser() == viewModel.getState().getOpp()) {
                    pageTitle.setText("TIE!");
                } else {
                    pageTitle.setText("You LOSE!");
                }

                yourScore.setText("You got: " + viewModel.getState().getUser());
                theirScore.setText("They got: " + viewModel.getState().getOpp());
            }
        });

    }

    private void cancelButtonPressed(ActionEvent event) {
        viewManager.navigate("main");

    }

}
