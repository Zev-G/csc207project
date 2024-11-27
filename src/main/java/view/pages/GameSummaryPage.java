package view.pages;

import interface_adapter.ViewModel;
import interface_adapter.game.GameSummaryPageState;
import interface_adapter.game.GameSummaryPageViewModel;
import view.View;
import view.app.App;
import view.components.standard.DLabel;
import view.components.standard.DPanel;
import view.components.standard.HorizontalPanel;
import view.components.standard.RoundedButton;
import view.components.standard.VerticalPanel;
import view.components.stats.GameSummaryPanel;
import view.utils.HTMLTextBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameSummaryPage extends Page implements View<GameSummaryPageState> {

    // UI Fields
    private final DLabel subtitleText = new DLabel(
            new HTMLTextBuilder()
                    .addText("--Game Summary--")
                    .center().build()
    );
    private final GameSummaryPanel gameSummaryPanel = new GameSummaryPanel();
    private final DPanel buttonsPanel = new DPanel();
    private final JButton backButton = new RoundedButton("Home");

    // ViewModel
    private final GameSummaryPageViewModel viewModel;

    public GameSummaryPage(App app) {
        super(app.getViewManager());
        this.viewModel = app.getGameSummaryPageViewModel();

        // Configure components
        subtitleText.setFontSize(80); // Updated font size for Game Summary
        subtitleText.setHorizontalAlignment(SwingConstants.CENTER);

        // Layout configuration
        setMargin(50); // Add margin to push everything slightly down
        setLayout(new BorderLayout());
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Configure back button
        backButton.setPreferredSize(new Dimension(200, 80));
        backButton.addActionListener(this::backButtonPressed);
        buttonsPanel.add(backButton); // Ensure the button is added to the buttonsPanel

        // Add components to the page
        add(new VerticalPanel(
                new HorizontalPanel(subtitleText)
        ), BorderLayout.PAGE_START);
        add(gameSummaryPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.PAGE_END);

        // Load state
        loadCurrentState();
        viewModel.addPropertyChangeListener(evt -> loadCurrentState());
    }

    private void backButtonPressed(ActionEvent event) {
        viewManager.navigate("main");
    }

    @Override
    public void loadState(GameSummaryPageState state) {
        if (state != null) {
            // Update stats
            gameSummaryPanel.updateSummary(state.getPoints(), state.getGuessBar());
        }
    }

    @Override
    public ViewModel<GameSummaryPageState> getViewModel() {
        return viewModel;
    }
}
