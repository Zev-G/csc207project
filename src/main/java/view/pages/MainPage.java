package view.pages;

import interface_adapter.ViewModel;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import view.View;
import view.ViewConstants;
import view.components.leaderboard.LeaderboardView;
import view.components.standard.*;
import view.utils.HTMLTextBuilder;

import javax.swing.*;
import java.awt.*;

public class MainPage extends Page implements View<AccountState> {

    // UI Fields
    private final DLabel uoftText = new SerifLabel("UofT");
    private final DLabel gameText = new DLabel("Guessr");
    private final DLabel subtitleText = new DLabel(
            new HTMLTextBuilder()
                    .addText("Test your knowledge of UofT by guessing")
                    .addLineBreak()
                    .addText("where these photos were taken")
                    .center().build());
    private final DPanel titlePanel = new HorizontalPanel(uoftText, gameText);
    private final DPanel subtitlePanel = new HorizontalPanel(subtitleText);

    private final JButton actionButton = new JButton();

    private final LeaderboardView leaderboard;
    private final VerticalPanel titleLayout = new VerticalPanel(titlePanel, subtitlePanel);

    // View Model
    private final AccountViewModel viewModel;

    public MainPage(LeaderboardViewModel lbvm, AccountViewModel viewModel, PageManager pageManager) {
        super(pageManager);
        this.viewModel = viewModel;

        // Create objects
        leaderboard = new LeaderboardView(lbvm);

        // Configure components
        uoftText.setFontSize(ViewConstants.TEXT_LL);
        gameText.setFontSize(ViewConstants.TEXT_LL);

        setMargin(ViewConstants.MARGIN_M);
        subtitleText.setHorizontalAlignment(SwingConstants.CENTER);

        BorderLayout layout = new BorderLayout();
        layout.setVgap(60);
        setLayout(layout);

        // Add components
        add(titleLayout, BorderLayout.PAGE_START);
        add(leaderboard, BorderLayout.CENTER);

        // Load state
        loadCurrentState();
        viewModel.addPropertyChangeListener(evt -> loadCurrentState());
    }


    @Override
    public void loadState(AccountState state) {
        if (state.isLoggedIn()) {
            actionButton.setText("Play");
        } else {
            actionButton.setText("Log in");
        }
    }

    @Override
    public ViewModel<AccountState> getViewModel() {
        return viewModel;
    }
}
