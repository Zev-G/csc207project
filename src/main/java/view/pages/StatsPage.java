package view.pages;

import interface_adapter.stats.StatsPageViewModel;
import view.app.App;
import view.components.stats.StatsPanel;
import view.components.standard.DLabel;
import view.components.standard.DPanel;
import view.components.standard.HorizontalPanel;
import view.components.standard.VerticalPanel;
import view.utils.HTMLTextBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StatsPage extends Page {

    // UI Fields
    private final DLabel usernameLabel = new DLabel("Username: -");
    private final DLabel subtitleText = new DLabel(
            new HTMLTextBuilder()
                    .addText("Review your performance so far:")
                    .center().build()
    );
    private final StatsPanel statsPanel = new StatsPanel();
    private final DPanel buttonsPanel = new DPanel();

    // ViewModel
    private final StatsPageViewModel viewModel;

    public StatsPage(App app) {
        super(app.getViewManager());
        this.viewModel = app.getStatsPageViewModel();

        // Configure components
        usernameLabel.setFont(new Font("Impact", Font.BOLD, 75)); // Sporty font for username
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        subtitleText.setFontSize(28);
        subtitleText.setHorizontalAlignment(SwingConstants.CENTER);

        // Layout configuration
        setMargin(20);
        setLayout(new BorderLayout());
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Add buttons (if needed, e.g., back button)
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(200, 80));
        backButton.addActionListener(this::onBackButtonPressed);
        buttonsPanel.add(backButton);

        // Add components
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

    private void onBackButtonPressed(ActionEvent event) {
        viewManager.back();
    }

    private void loadCurrentState() {
        var state = viewModel.getState();
        if (state != null) {
            // Update username
            usernameLabel.setText(state.getUsername());

            // Update stats
            statsPanel.updateStats(state.getPoints(), state.getGamesPlayed(), state.getCorrectGuesses());
        }
    }

    public void loadState(Object state) {
        loadCurrentState();
    }

    @Override
    public void init() {
        loadCurrentState();
    }
}
