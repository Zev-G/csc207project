package view.pages;

import interface_adapter.ViewModel;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import view.View;
import view.ViewConstants;
import view.components.AppViewManager;
import view.components.leaderboard.LeaderboardView;
import view.components.standard.*;
import view.utils.HTMLTextBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
    private final JButton multiplayerButton = new RoundedButton("Play Multiplayer");
    private final JButton accountButton = new RoundedButton("Account");

    // New Upload Image button
    private final JButton uploadImageButton = new RoundedButton("Upload Image");

    private final DPanel buttons = new DPanel();
    private final LeaderboardView leaderboard;
    private final VerticalPanel titleLayout = new VerticalPanel(titlePanel, subtitlePanel);

    // View Model
    private final AccountViewModel viewModel;

    public MainPage(AppViewManager app) {
        super(app.getViewManager());
        this.viewModel = app.getAccountViewModel();

        // Create objects
        leaderboard = new LeaderboardView(app.getLeaderboardViewModel());

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
        multiplayerButton.setPreferredSize(new Dimension(200, 80));
        uploadImageButton.setPreferredSize(new Dimension(200, 80)); // New button size

        // Add listeners
        loginButton.addActionListener(this::loginButtonPressed);
        signupButton.addActionListener(this::signupButtonPressed);
        playButton.addActionListener(this::playButtonPressed);
        multiplayerButton.addActionListener(this::multiplayerButtonPressed);
        accountButton.addActionListener(this::accountButtonPressed);
        uploadImageButton.addActionListener(this::uploadImageButtonPressed); // New listener

        // Add components
        add(titleLayout, BorderLayout.PAGE_START);
        add(leaderboard, BorderLayout.CENTER);
        add(buttons, BorderLayout.PAGE_END);

        // Load state
        loadCurrentState();
        viewModel.addPropertyChangeListener(evt -> loadCurrentState());
    }

    private void playButtonPressed(ActionEvent event) {
        viewManager.navigate("game");
    }

    private void multiplayerButtonPressed(ActionEvent event) {
        viewManager.navigate("multiplayer");
    }

    private void accountButtonPressed(ActionEvent event) {
        viewManager.navigate("account");
    }

    private void uploadImageButtonPressed(ActionEvent event) {
        viewManager.navigate("image"); // Navigate to the new ImagePage
    }

    private void loginButtonPressed(ActionEvent event) {
        viewModel.setState(new AccountState(true, "Zev", "godfreyzev@gmail.com", "1234", 1));
    }

    private void signupButtonPressed(ActionEvent event) {
        viewModel.setState(new AccountState(true, "Zev", "godfreyzev@gmail.com", "1234", 1));
    }

    @Override
    public void loadState(AccountState state) {
        buttons.removeAll();
        if (state.isLoggedIn()) {
            buttons.add(playButton);
            buttons.add(multiplayerButton);
            buttons.add(accountButton);
            buttons.add(uploadImageButton); // Add Upload Image button for logged-in users
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
