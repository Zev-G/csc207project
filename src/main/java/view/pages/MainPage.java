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

/**
 * Represents the main page of the application, which provides navigation to different
 * parts of the app, including login, signup, single-player, multiplayer, account, and image upload.
 */
public class MainPage extends Page implements View<AccountState> {

    // UI Fields
    /** Label displaying "UofT". */
    private final DLabel uoftText = new SerifLabel("UofT");

    /** Label displaying "Guessr". */
    private final DLabel gameText = new DLabel("Guessr");

    /** Subtitle label with description text. */
    private final DLabel subtitleText = new DLabel(
            new HTMLTextBuilder()
                    .addText("Test your knowledge of UofT by guessing")
                    .addLineBreak()
                    .addText("where these photos were taken")
                    .center().build());

    /** Panel containing the title components. */
    private final DPanel titlePanel = new HorizontalPanel(uoftText, gameText);

    /** Panel containing the subtitle text. */
    private final DPanel subtitlePanel = new HorizontalPanel(subtitleText);

    /** Button for navigating to the login page. */
    private final JButton loginButton = new RoundedButton("Login");

    /** Button for navigating to the signup page. */
    private final JButton signupButton = new RoundedButton("Signup");

    /** Button for starting a single-player game. */
    private final JButton playButton = new RoundedButton("Play");

    /** Button for starting a multiplayer game. */
    private final JButton multiplayerButton = new RoundedButton("Play Multiplayer");

    /** Button for navigating to the account page. */
    private final JButton accountButton = new RoundedButton("Account");

    /** Button for navigating to the image upload page. */
    private final JButton uploadImageButton = new RoundedButton("Upload Image");

    /** Panel for holding navigation buttons. */
    private final DPanel buttons = new DPanel();

    /** Leaderboard view displayed on the main page. */
    private final LeaderboardView leaderboard;

    /** Layout for organizing the title and subtitle components. */
    private final VerticalPanel titleLayout = new VerticalPanel(titlePanel, subtitlePanel);

    /** View model for handling account state. */
    private final AccountViewModel viewModel;

    /**
     * Constructs a new main page.
     *
     * @param app The app view manager for managing navigation and view models.
     */
    public MainPage(AppViewManager app) {
        super(app.getViewManager());
        this.viewModel = app.getAccountViewModel();

        // Create leaderboard object
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

        // Set button sizes
        loginButton.setPreferredSize(new Dimension(200, 80));
        signupButton.setPreferredSize(new Dimension(200, 80));
        accountButton.setPreferredSize(new Dimension(200, 80));
        playButton.setPreferredSize(new Dimension(200, 80));
        multiplayerButton.setPreferredSize(new Dimension(200, 80));
        uploadImageButton.setPreferredSize(new Dimension(200, 80));

        // Add action listeners to buttons
        loginButton.addActionListener(this::loginButtonPressed);
        signupButton.addActionListener(this::signupButtonPressed);
        playButton.addActionListener(this::playButtonPressed);
        multiplayerButton.addActionListener(this::multiplayerButtonPressed);
        accountButton.addActionListener(this::accountButtonPressed);
        uploadImageButton.addActionListener(this::uploadImageButtonPressed);

        // Add components to the layout
        add(titleLayout, BorderLayout.PAGE_START);
        add(leaderboard, BorderLayout.CENTER);
        add(buttons, BorderLayout.PAGE_END);

        // Load the initial state
        loadCurrentState();
        viewModel.addPropertyChangeListener(evt -> loadCurrentState());
    }

    /**
     * Handles the play button press event, navigating to the game page.
     *
     * @param event The action event triggered by the button press.
     */
    private void playButtonPressed(ActionEvent event) {
        viewManager.navigate("game");
    }

    /**
     * Handles the multiplayer button press event, navigating to the multiplayer page.
     *
     * @param event The action event triggered by the button press.
     */
    private void multiplayerButtonPressed(ActionEvent event) {
        viewManager.navigate("multiplayer");
    }

    /**
     * Handles the account button press event, navigating to the account page.
     *
     * @param event The action event triggered by the button press.
     */
    private void accountButtonPressed(ActionEvent event) {
        viewManager.navigate("account");
    }

    /**
     * Handles the upload image button press event, navigating to the image upload page.
     *
     * @param event The action event triggered by the button press.
     */
    private void uploadImageButtonPressed(ActionEvent event) {
        viewManager.navigate("image");
    }

    /**
     * Handles the login button press event, simulating a user login.
     *
     * @param event The action event triggered by the button press.
     */
    private void loginButtonPressed(ActionEvent event) {
        viewManager.navigate("login");
    }

    /**
     * Handles the signup button press event, simulating a user signup.
     *
     * @param event The action event triggered by the button press.
     */
    private void signupButtonPressed(ActionEvent event) {
        viewManager.navigate("signup");
    }

    /**
     * Loads the current account state and updates the UI components accordingly.
     *
     * @param state The current account state to load.
     */
    @Override
    public void loadState(AccountState state) {
        buttons.removeAll();
        if (state.isLoggedIn()) {
            buttons.add(playButton);
            buttons.add(multiplayerButton);
            buttons.add(accountButton);
            buttons.add(uploadImageButton);
        } else {
            buttons.add(loginButton);
            buttons.add(signupButton);
        }
        buttons.revalidate();
        buttons.repaint();
    }

    /**
     * Retrieves the view model for the main page.
     *
     * @return The view model associated with this page.
     */
    @Override
    public ViewModel<AccountState> getViewModel() {
        return viewModel;
    }
}
