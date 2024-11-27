package view.pages;

import interface_adapter.account.AccountViewModel;
import interface_adapter.game.GameController;
import interface_adapter.game.GameSummaryController;
import interface_adapter.game.GameViewModel;
import view.app.App;
import view.components.game.GameTimer;
import view.components.game.InteractiveMap;
import view.components.game.PointsDisplay;
import view.components.standard.RoundedButton;
import view.components.game.SegmentedProgressBar;
import view.utils.ImageScaler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GamePage extends Page {

    private JLabel roundLabel;
    private SegmentedProgressBar progressBar;
    private GameTimer gameTimer;
    private PointsDisplay pointsDisplay;
    private RoundedButton guessButton;
    private RoundedButton viewSummaryButton;
    private RoundedButton homeButton; // New Home button
    private JLabel imageLabel1;

    private InteractiveMap map =
            new InteractiveMap(new ImageIcon(ClassLoader.getSystemResource("photos/UofTmap.jpg")),
                    new double[]{43.66997811270511, 43.657184780883696, -79.40326917196147, -79.3848918572115});

    private GameController gameController;
    private GameSummaryController gameSummaryController;
    private GameViewModel gameViewModel;
    private final AccountViewModel aCViewModel;

    public GamePage(App app) {
        super(app.getViewManager());

        setMargin(20);

        this.gameController = app.getGameController();
        this.gameViewModel = app.getGameViewModel();
        this.gameSummaryController = app.getGameSummaryController();
        this.aCViewModel = app.getAccountViewModel();

        setLayout(new BorderLayout());

        setMargin(50);

        // Top panel configuration
        JPanel topPanel = new JPanel(new BorderLayout());
        roundLabel = new JLabel("Round " + gameViewModel.getState().getRound());
        roundLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(roundLabel, BorderLayout.WEST);

        gameTimer = new GameTimer(60);
        pointsDisplay = new PointsDisplay(0);

        JPanel timerPanel = new JPanel(new BorderLayout());
        gameTimer.getTimerLabel().setFont(new Font("Arial", Font.BOLD, 24));
        pointsDisplay.getPointsLabel().setFont(new Font("Arial", Font.BOLD, 24));
        timerPanel.add(gameTimer.getTimerLabel(), BorderLayout.WEST);
        timerPanel.add(pointsDisplay.getPointsLabel(), BorderLayout.CENTER);
        topPanel.add(timerPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Main center panel configuration
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = (int) (screenSize.width * 0.35);

        JPanel mainCenterPanel = new JPanel();
        mainCenterPanel.setLayout(new BoxLayout(mainCenterPanel, BoxLayout.Y_AXIS));
        mainCenterPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        mainCenterPanel.add(Box.createVerticalGlue());

        JPanel imagePanel = new JPanel(new GridLayout(1, 2, 10, 0));

        imageLabel1 = new JLabel();
        imageLabel1.setHorizontalAlignment(JLabel.CENTER);
        imageLabel1.setPreferredSize(new Dimension(frameWidth, frameWidth));

        imagePanel.add(imageLabel1);

        mainCenterPanel.setLayout(new BoxLayout(mainCenterPanel, BoxLayout.X_AXIS));
        mainCenterPanel.add(imagePanel);
        mainCenterPanel.add(map);
        mainCenterPanel.add(Box.createVerticalGlue());

        add(mainCenterPanel, BorderLayout.CENTER);

        // Bottom panel with buttons
        guessButton = new RoundedButton("Guess");
        guessButton.setPreferredSize(new Dimension(200, 80));
        guessButton.addActionListener(e -> {
            System.out.println("clicked");
            gameController.handleGuess(
                    gameViewModel.getState().getPhotoID(),
                    gameViewModel.getState().getTarget(),
                    map.getChosenCoord()
            );
        });

        viewSummaryButton = new RoundedButton("View Summary");
        viewSummaryButton.setPreferredSize(new Dimension(200, 80));
        viewSummaryButton.addActionListener(e -> {
            //PASS IN GAME STATE POINTS AND PROGRESS BAR STUFF
            gameSummaryController.fetchGameStats(progressBar.getAllSegmentStatus(), gameViewModel.getState().getScore(), aCViewModel.getState().getUsername());
            viewManager.navigate("summary");
            System.out.println("View Summary clicked");
        });
        viewSummaryButton.setVisible(false); // Initially hidden

        homeButton = new RoundedButton("Home"); // New Home button
        homeButton.setPreferredSize(new Dimension(200, 80));
        homeButton.addActionListener(e -> {
            System.out.println("Home clicked");
            viewManager.navigate("main"); // Handle Home action in the controller
        });
        homeButton.setVisible(false); // Initially hidden

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        buttonPanel.add(guessButton);
        buttonPanel.add(viewSummaryButton); // Add View Summary button
        buttonPanel.add(homeButton); // Add Home button
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);

        progressBar = new SegmentedProgressBar(10);
        JPanel progressBarPanel = new JPanel(new BorderLayout());
        progressBarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        progressBarPanel.add(progressBar, BorderLayout.CENTER);

        bottomPanel.add(progressBarPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // Add property change listeners
        gameViewModel.addPropertyChangeListener(evt -> {
            if (gameViewModel.getState().getNextPhoto() != null) {
                imageLabel1.setIcon(ImageScaler.getScaledImageIcon(
                        gameViewModel.getState().getNextPhoto(),
                        frameWidth,
                        frameWidth
                ));
            }
            if (gameViewModel.getState().getRound() != 1) {
                progressBar.updateRound(gameViewModel.getState().isAcceptable());
            }

            System.out.println(gameViewModel.getState().getScore());
            pointsDisplay.setPoints(gameViewModel.getState().getScore());

            // Update visibility of View Summary button
            viewSummaryButton.setVisible(gameViewModel.getState().shouldShow());

            // Update visibility of Home button (e.g., another condition)
            homeButton.setVisible(gameViewModel.getState().shouldShow());
        });

        gameTimer.addPropertyChangeListener(evt -> System.out.println("timeout"));
    }

    @Override
    public void init() {
        gameController.init();
        gameTimer.resetTimer();
        gameTimer.start();

        // Reset the progress bar
        progressBar.reset(); // Ensure progress bar is cleared when the page is initialized
    }

}