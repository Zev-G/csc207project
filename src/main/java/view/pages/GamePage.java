package view.pages;

import interface_adapter.game.GameController;
import interface_adapter.game.GameViewModel;
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


public class GamePage extends Page {

    private JLabel roundLabel;
    private SegmentedProgressBar progressBar;
    private GameTimer gameTimer;
    private PointsDisplay pointsDisplay;
    private RoundedButton guessButton;
    private JLabel imageLabel1;

    private InteractiveMap map =
            new InteractiveMap(new ImageIcon(ClassLoader.getSystemResource("photos/UofTmap.jpg")),
                    new double[]{43.66997811270511, 43.657184780883696, -79.40326917196147, -79.3848918572115});

    private GameController gameController;

    private GameViewModel gameViewModel;

    public GamePage(PageManager pageManager, GameViewModel gameViewModel, GameController gameController) {
        super(pageManager);

        setMargin(20);

        this.gameController = gameController;
        this.gameViewModel = gameViewModel;

        setLayout(new BorderLayout());

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

        guessButton = new RoundedButton("Guess");
        guessButton.setPreferredSize(new Dimension(200, 80));
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("clicked");
                gameController.handleGuess(gameViewModel.getState().getPhotoID(), gameViewModel.getState().getTarget(),
                        map.getChosenCoord());
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        buttonPanel.add(guessButton);
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);

        progressBar = new SegmentedProgressBar(10);
        JPanel progressBarPanel = new JPanel(new BorderLayout());
        progressBarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        progressBarPanel.add(progressBar, BorderLayout.CENTER);

        bottomPanel.add(progressBarPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        gameViewModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (gameViewModel.getState().getNextPhoto() != null) {
                    imageLabel1.setIcon(ImageScaler.getScaledImageIcon(gameViewModel.getState().getNextPhoto(), frameWidth, frameWidth));
                }
                if (gameViewModel.getState().getRound() != 1) {
                    progressBar.updateRound(gameViewModel.getState().isAcceptable());
                }

                System.out.println(gameViewModel.getState().getScore());
                pointsDisplay.setPoints(gameViewModel.getState().getScore());
            }
        });

        gameTimer.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("timeout");
            }
        });
    }

    @Override
    public void init() {
        gameTimer.resetTimer();
        gameTimer.start();
    }
}
