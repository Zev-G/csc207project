package view.pages;

import interface_adapter.ViewModel;
import interface_adapter.account.AccountViewModel;
import interface_adapter.stats.StatsController;
import interface_adapter.stats.StatsPageState;
import interface_adapter.stats.StatsPageViewModel;
import view.View;
import view.components.AppViewManager;
import view.components.standard.DLabel;
import view.components.standard.DPanel;
import view.components.standard.HorizontalPanel;
import view.components.standard.RoundedButton;
import view.components.standard.VerticalPanel;
import view.components.stats.StatsPanel;
import view.utils.HTMLTextBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * StatsPage represents a page displaying user stats, including points, games played, and correct guesses.
 * Implements the View interface for StatsPageState.
 */
public class StatsPage extends Page implements View<StatsPageState> {

    // UI Fields
    private final DLabel usernameLabel = new DLabel("Username: -");
    private final DLabel subtitleText = new DLabel(
            new HTMLTextBuilder()
                    .addText("--Your Stats--")
                    .center().build()
    );
    private final StatsPanel statsPanel = new StatsPanel();
    private final DPanel buttonsPanel = new DPanel();
    private final JButton backbutton = new RoundedButton("Back");

    // ViewModel
    private final StatsPageViewModel viewModel;
    private final StatsController statsController;
    private final AccountViewModel aCViewModel;

    /**
     * Constructor to initialize the StatsPage with necessary components and ViewModels.
     * @param app The AppViewManager that provides the required ViewModels and controllers.
     */
    public StatsPage(AppViewManager app) {
        super(app.getViewManager());
        this.viewModel = app.getStatsPageViewModel();
        this.statsController = app.getStatsController();
        this.aCViewModel = app.getAccountViewModel();

        // Configure components
        usernameLabel.setFont(new Font("Impact", Font.BOLD, 75)); // Sporty font for username
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        subtitleText.setFontSize(28);
        subtitleText.setHorizontalAlignment(SwingConstants.CENTER);

        // Layout configuration
        setMargin(20);
        setLayout(new BorderLayout());
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Configure back button
        backbutton.setPreferredSize(new Dimension(200, 80));
        backbutton.addActionListener(this::backButtonPressed);
        buttonsPanel.add(backbutton); // Ensure the button is added to the buttonsPanel

        // Add components to the page
        add(new VerticalPanel(
                new HorizontalPanel(usernameLabel),
                new HorizontalPanel(subtitleText)
        ), BorderLayout.PAGE_START);
        add(statsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.PAGE_END);

        // Load state
        loadCurrentState();
        viewModel.addPropertyChangeListener(evt -> loadCurrentState());
    }

    /**
     * Handles the back button press event to navigate back to the previous page.
     * @param event The action event triggered by the back button press.
     */
    private void backButtonPressed(ActionEvent event) {
        viewManager.back();
    }

    /**
     * Loads the state into the view components such as username and stats.
     * @param state The StatsPageState containing the data to display.
     */
    @Override
    public void loadState(StatsPageState state) {
        if (state != null) {
            // Update username
            usernameLabel.setText(state.getUsername());

            // Update stats
            statsPanel.updateStats(state.getPoints(), state.getGamesPlayed(), state.getCorrectGuesses());
        }
    }

    /**
     * Returns the ViewModel associated with the StatsPage.
     * @return The ViewModel for StatsPageState.
     */
    @Override
    public ViewModel<StatsPageState> getViewModel() {
        return viewModel;
    }

    /**
     * Initializes the page by fetching the current user's stats.
     */
    public void init() {
        // Fetch stats for the current user (e.g., DemoUser)
        statsController.fetchStats(aCViewModel.getState().getUsername());
    }
}
