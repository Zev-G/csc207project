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

/**
 * Page displaying the end of a multiplayer game with scores and results.
 */
public class EndMultiplayerGamePage extends Page {

    private final MGameEndViewModel viewModel;

    private final DLabel pageTitle = new DLabel("");
    private final VerticalPanel titleLayout = new VerticalPanel(pageTitle);
    private final DLabel yourScore = new DLabel("You got: 0");
    private final DLabel theirScore = new DLabel("They got: 0");

    private final JButton cancelButton = new RoundedButton("Return to main");
    private final DPanel buttons = new DPanel();
    private final DPanel grid = new DPanel();

    /**
     * Constructs an EndMultiplayerGamePage with the specified app view manager.
     *
     * @param app the application view manager
     */
    public EndMultiplayerGamePage(AppViewManager app) {
        super(app.getViewManager());

        this.viewModel = app.getmGameEndViewModel();

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
     * Configures the grid layout for displaying scores and title.
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

        // User score
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        grid.add(yourScore, gbc);

        // Opponent score
        gbc.gridx = 0;
        gbc.gridy = 2;
        grid.add(theirScore, gbc);
    }

    /**
     * Sets up listeners for the cancel button and property changes.
     */
    private void setupListeners() {
        cancelButton.addActionListener(this::cancelButtonPressed);

        viewModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateScoresAndTitle();
            }
        });
    }

    /**
     * Handles the cancel button press event.
     *
     * @param event the action event triggered by button press
     */
    private void cancelButtonPressed(ActionEvent event) {
        viewManager.navigate("main");
    }

    /**
     * Updates the page title and scores based on the game state.
     */
    private void updateScoresAndTitle() {
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
}
