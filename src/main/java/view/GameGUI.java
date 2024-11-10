package view;

import game.DataAccessMock;
import game.PhotoLocationFactory;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;

public class GameGUI extends JFrame {
    private JLabel roundLabel;
    private SegmentedProgressBar progressBar;
    private GameTimer gameTimer;
    private PointsDisplay pointsDisplay;
    private RoundedButton guessButton;
    private JLabel imageLabel1;
    private JLabel imageLabel2;

    private int round = 1;
    private int totalRounds = 10;

    public GameGUI() {
        setTitle("Game GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        roundLabel = new JLabel("Round " + round);
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

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = (int) (screenSize.width * 0.35);

        JPanel mainCenterPanel = new JPanel();
        mainCenterPanel.setLayout(new BoxLayout(mainCenterPanel, BoxLayout.Y_AXIS));
        mainCenterPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        mainCenterPanel.add(Box.createVerticalGlue());

        JPanel imagePanel = new JPanel(new GridLayout(1, 2, 10, 0));
        imageLabel1 = new JLabel();
        imageLabel2 = new JLabel();

        imageLabel1.setHorizontalAlignment(JLabel.CENTER);
        imageLabel2.setHorizontalAlignment(JLabel.CENTER);

        imageLabel1.setPreferredSize(new Dimension(frameWidth, frameWidth));
        imageLabel2.setPreferredSize(new Dimension(frameWidth, frameWidth));

        loadNewImages(frameWidth);

        imagePanel.add(imageLabel1);
        imagePanel.add(imageLabel2);

        mainCenterPanel.add(imagePanel);
        mainCenterPanel.add(Box.createVerticalGlue());

        add(mainCenterPanel, BorderLayout.CENTER);

        guessButton = new RoundedButton("Guess");
        guessButton.setPreferredSize(new Dimension(200, 80));
        guessButton.addActionListener(e -> handleGuess(frameWidth));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        buttonPanel.add(guessButton);
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);

        progressBar = new SegmentedProgressBar(totalRounds);
        JPanel progressBarPanel = new JPanel(new BorderLayout());
        progressBarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        progressBarPanel.add(progressBar, BorderLayout.CENTER);

        bottomPanel.add(progressBarPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);

        gameTimer.start(this::nextRound);
    }

    private void loadNewImages(int frameWidth) {
        DataAccessMock dataAccess = new DataAccessMock();
        PhotoLocationFactory photoFactory = new PhotoLocationFactory(dataAccess);

        ImageIcon fetchedImage1 = photoFactory.getRandomLocation().getPhoto();
        imageLabel1.setIcon(ImageScaler.getScaledImageIcon(fetchedImage1, frameWidth, frameWidth));

        try {
            // The following line sets the RHS image. Ideally, this should be replaced
            // with an interactive map embedding for a more engaging user experience.
            ImageIcon mapImage = new ImageIcon(ImageIO.read(getClass().getResource("/photos/UofTmap.jpg")));
            imageLabel2.setIcon(ImageScaler.getScaledImageIcon(mapImage, frameWidth, frameWidth));
        } catch (IOException e) {
            e.printStackTrace();
            imageLabel2.setText("Map not found");
        }
    }



    private void handleGuess(int frameWidth) {
        boolean win = Math.random() > 0.5;
        boolean barFilled = progressBar.updateRound(win);

        if (!barFilled) {
            round++;
            roundLabel.setText("Round " + round);
            loadNewImages(frameWidth);
        } else {
            guessButton.setText("End Game");
        }
    }

    private void nextRound() {
        if (round < totalRounds) {
            round++;
            roundLabel.setText("Round " + round);
            gameTimer.reset(60);
            gameTimer.start(this::nextRound);
            progressBar.updateRound(Math.random() > 0.5);
            pointsDisplay.incrementPoints(100);
        }
    }

    public static void main(String[] args) {
        new GameGUI();
    }
}
