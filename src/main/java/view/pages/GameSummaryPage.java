package view.pages;

import interface_adapter.ViewModel;
import interface_adapter.game.GameSummaryPageState;
import interface_adapter.game.GameSummaryPageViewModel;
import view.View;
import view.components.AppViewManager;
import view.components.game.GameSummaryPanel;
import view.components.standard.DLabel;
import view.components.standard.DPanel;
import view.components.standard.HorizontalPanel;
import view.components.standard.RoundedButton;
import view.components.standard.VerticalPanel;
import view.utils.HTMLTextBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Displays the game summary page with points, guess bar, and navigation options.
 */
public class GameSummaryPage extends Page implements View<GameSummaryPageState> {

    // UI Components
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

    /**
     * Constructs a GameSummaryPage with the specified app view manager.
     *
     * @param app the application view manager
     */
    public GameSummaryPage(AppViewManager app) {
        super(app.getViewManager());
        this.viewModel = app.getGameSummaryPageViewModel();

        configureComponents();
        configureLayout();
        loadCurrentState();
        viewModel.addPropertyChangeListener(evt -> loadCurrentState());
    }

    /**
     * Configures UI components.
     */
    private void configureComponents() {
        subtitleText.setFontSize(80);
        subtitleText.setHorizontalAlignment(SwingConstants.CENTER);

        backButton.setPreferredSize(new Dimension(200, 80));
        backButton.addActionListener(this::backButtonPressed);
    }

    /**
     * Configures the layout of the page.
     */
    private void configureLayout() {
        setMargin(50);
        setLayout(new BorderLayout());

        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonsPanel.add(backButton);

        add(new VerticalPanel(
                new HorizontalPanel(subtitleText)
        ), BorderLayout.PAGE_START);
        add(gameSummaryPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.PAGE_END);
    }

    /**
     * Handles the "Home" button press event, navigating back to the main page.
     *
     * @param event the action event triggered by button press
     */
    private void backButtonPressed(ActionEvent event) {
        viewManager.navigate("main");
    }

    /**
     * Updates the UI with the provided state.
     *
     * @param state the current state of the GameSummaryPage
     */
    @Override
    public void loadState(GameSummaryPageState state) {
        if (state != null) {
            gameSummaryPanel.updateSummary(state.getPoints(), state.getGuessBar());
        }
    }

    /**
     * Returns the ViewModel associated with this page.
     *
     * @return the ViewModel for GameSummaryPageState
     */
    @Override
    public ViewModel<GameSummaryPageState> getViewModel() {
        return viewModel;
    }
}
