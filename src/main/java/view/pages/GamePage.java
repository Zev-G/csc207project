package view.pages;

import interface_adapter.game.GameController;
import interface_adapter.game.GameViewModel;
import view.components.AppViewManager;
import view.components.game.GameTimer;
import view.components.game.InteractiveMap;
import view.components.game.PointsDisplay;
import view.components.standard.RoundedButton;
import view.components.game.SegmentedProgressBar;
import view.utils.ImageScaler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * GamePage displays the main gameplay interface, including a map, timer, points,
 * progress bar, and navigation controls.
 */
public class GamePage extends Page {

    private SegmentedProgressBar progressBar;
    protected GameTimer gameTimer;
    private PointsDisplay pointsDisplay;
    private RoundedButton guessButton;
    private RoundedButton summaryButton;
    private RoundedButton homeButton;
    private JLabel imageLabel1;

    private final InteractiveMap map;
    private final GameController gameController;
    private final GameViewModel gameViewModel;

    private final AppViewManager app;

    /**
     * Constructs a GamePage with the specified app, game controller, and game view model.
     *
     * @param app            the application view manager
     * @param gameController the controller managing game logic
     * @param gameViewModel  the view model for game data
     */
    public GamePage(AppViewManager app, GameController gameController, GameViewModel gameViewModel) {
        super(app.getViewManager());
        this.app = app;
        this.gameController = gameController;
        this.gameViewModel = gameViewModel;

        map = new InteractiveMap(
                new ImageIcon(ClassLoader.getSystemResource("photos/UofTmap.jpg")),
                new double[]{43.66997811270511, 43.657184780883696, -79.40326917196147, -79.3848918572115});

        setLayout(new BorderLayout());
        setMargin(50);

        setupTopPanel();
        setupMainPanel();
        setupBottomPanel();

        gameViewModel.addPropertyChangeListener(this::onGameStateChange);
        gameTimer.addPropertyChangeListener(evt -> onTimeout());
    }

    /**
     * Initializes the game page and starts the game.
     */
    @Override
    public void init() {
        gameController.init();
        resetPage();
    }

    /**
     * Resets the page to its initial state, including resetting the progress bar and timer.
     */
    protected void resetPage() {
        progressBar.reset();
        gameTimer.resetTimer();
        gameTimer.start();
    }

    /**
     * Handles game over events by resetting the map and timer.
     */
    private void gameOver() {
        map.reset();
        gameTimer.resetTimer();
    }

    /**
     * Sets up the top panel containing the game timer and points display.
     */
    private void setupTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());

        gameTimer = new GameTimer(60);
        pointsDisplay = new PointsDisplay(0);

        JPanel timerPanel = new JPanel(new BorderLayout());
        gameTimer.getTimerLabel().setFont(new Font("Arial", Font.BOLD, 24));
        pointsDisplay.getPointsLabel().setFont(new Font("Arial", Font.BOLD, 24));
        timerPanel.add(gameTimer.getTimerLabel(), BorderLayout.WEST);
        timerPanel.add(pointsDisplay.getPointsLabel(), BorderLayout.CENTER);

        topPanel.add(timerPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
    }

    /**
     * Sets up the main panel containing the map and image display.
     */
    private void setupMainPanel() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = (int) (screenSize.width * 0.35);

        JPanel mainCenterPanel = new JPanel();
        mainCenterPanel.setLayout(new BoxLayout(mainCenterPanel, BoxLayout.X_AXIS));
        mainCenterPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JPanel imagePanel = new JPanel(new GridLayout(1, 2, 10, 0));
        imageLabel1 = new JLabel();
        imageLabel1.setHorizontalAlignment(JLabel.CENTER);
        imageLabel1.setPreferredSize(new Dimension(frameWidth, frameWidth));
        imagePanel.add(imageLabel1);

        mainCenterPanel.add(imagePanel);
        mainCenterPanel.add(map);
        add(mainCenterPanel, BorderLayout.CENTER);
    }

    /**
     * Sets up the bottom panel containing navigation buttons and progress bar.
     */
    private void setupBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        setupButtons(buttonPanel);
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);

        progressBar = new SegmentedProgressBar(10);
        JPanel progressBarPanel = new JPanel(new BorderLayout());
        progressBarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        progressBarPanel.add(progressBar, BorderLayout.CENTER);
        bottomPanel.add(progressBarPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Sets up the interactive buttons (Guess, Summary, and Home).
     *
     * @param buttonPanel the panel to add the buttons to
     */
    private void setupButtons(JPanel buttonPanel) {
        guessButton = new RoundedButton("Guess");
        guessButton.setPreferredSize(new Dimension(200, 80));
        guessButton.addActionListener(e -> handleGuess());

        summaryButton = new RoundedButton("Summary");
        summaryButton.setPreferredSize(new Dimension(200, 80));
        summaryButton.setVisible(false);
        summaryButton.addActionListener(e -> handleSummary(app));

        homeButton = new RoundedButton("Home");
        homeButton.setPreferredSize(new Dimension(200, 80));
        homeButton.setVisible(false);
        homeButton.addActionListener(e -> handleHome(app));

        buttonPanel.add(guessButton);
        buttonPanel.add(summaryButton);
        buttonPanel.add(homeButton);
    }

    /**
     * Handles the "Guess" button click event.
     */
    private void handleGuess() {
        gameController.handleGuess(gameViewModel.getState().getPhotoID(),
                gameViewModel.getState().getTarget(),
                map.getChosenCoord());
    }

    /**
     * Handles the "Summary" button click event.
     *
     * @param app the application view manager
     */
    private void handleSummary(AppViewManager app) {
        app.getGameSummaryController().fetchGameStats(
                progressBar.getAllSegmentStatus(),
                gameViewModel.getState().getScore(),
                app.getAccountViewModel().getState().getUsername(),
                progressBar.getCountTrueSegments());
        viewManager.navigate("summary");
    }

    /**
     * Handles the "Home" button click event.
     *
     * @param app the application view manager
     */
    private void handleHome(AppViewManager app) {
        app.getUpdateStatsController().updateStats(
                app.getAccountViewModel().getState().getUsername(),
                gameViewModel.getState().getScore(),
                progressBar.getCountTrueSegments());
        viewManager.navigate("main");
    }

    /**
     * Handles game state changes and updates the UI accordingly.
     *
     * @param evt the property change event
     */
    private void onGameStateChange(PropertyChangeEvent evt) {
        if (gameViewModel.getState().getNextPhoto() != null) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int frameWidth = (int) (screenSize.width * 0.35);
            imageLabel1.setIcon(ImageScaler.getScaledImageIcon(gameViewModel.getState().getNextPhoto(), frameWidth, frameWidth));
        }

        if (gameViewModel.getState().getRound() != 1) {
            progressBar.updateRound(gameViewModel.getState().isAcceptable());
        }

        if (gameViewModel.getState().isGameOver()) {
            gameOver();
        }

        pointsDisplay.setPoints(gameViewModel.getState().getScore());

        summaryButton.setVisible(gameViewModel.getState().isGameOver());
        homeButton.setVisible(gameViewModel.getState().isGameOver());
        guessButton.setVisible(!gameViewModel.getState().isGameOver());
    }

    /**
     * Handles timeout events.
     */
    private void onTimeout() {
        gameController.timeout(gameViewModel.getState().getPhotoID(),
                gameViewModel.getState().getTarget(),
                map.getChosenCoord());
    }
}
