package view.pages;

import data_access.DataAccessMock;
import entity.PhotoLocationFactory;
import entity.PhotoLocation;
import view.components.game.GameTimer;
import view.components.game.InteractiveMap;
import view.components.game.PointsDisplay;
import view.utils.ImageScaler;
import view.components.standard.RoundedButton;
import view.components.game.SegmentedProgressBar;

import javax.swing.*;
import java.awt.*;


public class GamePageNew extends Page {
    private JLabel roundLabel;
    private SegmentedProgressBar progressBar;
    private PointsDisplay pointsDisplay;
    private RoundedButton guessButton;
    private JLabel imageLabel1;

    private DataAccessMock dataAccess = new DataAccessMock();

    private double[] coord;
    private InteractiveMap map =
            new InteractiveMap(new ImageIcon(ClassLoader.getSystemResource("photos/UofTmap.jpg")),
                    new double[]{43.66997811270511, 43.657184780883696, -79.40326917196147, -79.3848918572115});

    private int round = 1;
    private int totalRounds = 10;

    public GamePageNew(PageManager pageManager) {
        super(pageManager);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        roundLabel = new JLabel("Round " + round);
        roundLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(roundLabel, BorderLayout.WEST);

        pointsDisplay = new PointsDisplay(0);

        JPanel timerPanel = new JPanel(new BorderLayout());
        pointsDisplay.getPointsLabel().setFont(new Font("Arial", Font.BOLD, 24));
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

    }
}
