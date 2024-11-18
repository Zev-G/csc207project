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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private final JButton loginButton = new RoundedButton("Login");
    private final JButton signupButton = new RoundedButton("Signup");
    private final JButton playButton = new RoundedButton("Play");
    private final JButton accountButton = new RoundedButton("Account");
    private final DPanel buttons = new DPanel();

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

        buttons.setLayout(new FlowLayout());

        loginButton.setPreferredSize(new Dimension(200, 80));
        signupButton.setPreferredSize(new Dimension(200, 80));
        accountButton.setPreferredSize(new Dimension(200, 80));
        playButton.setPreferredSize(new Dimension(200, 80));

        // Add listeners
        loginButton.addActionListener(this::loginButtonPressed);
        signupButton.addActionListener(this::signupButtonPressed);
        playButton.addActionListener(this::playButtonPressed);
        accountButton.addActionListener(this::accountButtonPressed);

        // Add components
        add(titleLayout, BorderLayout.PAGE_START);
        add(leaderboard, BorderLayout.CENTER);
        add(buttons, BorderLayout.PAGE_END);

        // Load state
        loadCurrentState();
        viewModel.addPropertyChangeListener(evt -> loadCurrentState());
    }

    private void playButtonPressed(ActionEvent event) {
        pageManager.navigate(new GamePage(pageManager));
    }

    private void accountButtonPressed(ActionEvent event) {
        pageManager.navigate(new AccountPage(viewModel, pageManager));
    }

    private void loginButtonPressed(ActionEvent event) {

    }

    private void signupButtonPressed(ActionEvent event) {

    }


    @Override
    public void loadState(AccountState state) {
        buttons.removeAll();
        if (state.isLoggedIn()) {
            buttons.add(playButton);
            buttons.add(accountButton);
        } else {
            buttons.add(loginButton);
            buttons.add(signupButton);
        }
        buttons.revalidate(); // Refreshes the layout
        buttons.repaint();    // Redraws the container
    }

    @Override
    public ViewModel<AccountState> getViewModel() {
        return viewModel;
    }
}
