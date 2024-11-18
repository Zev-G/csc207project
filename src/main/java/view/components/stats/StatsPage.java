package view;

import app.GetUserStatsUseCase;
import data_access.UserStatsRepositoryImpl;
import entity.UserStats;

import javax.swing.*;
import java.awt.*;

public class StatsPage extends JPanel {

    private Image backgroundImage;

    // UI Components
    private JLabel userNameLabel;
    private JLabel pointsLabel;
    private JLabel gamesLabel;
    private JLabel guessesLabel;

    private JButton backButton;
    private JButton seeGamesButton;

    public StatsPage(String username) {
        // Load the background image
        backgroundImage = new ImageIcon(getClass().getResource("/photos/statsbg.jpg")).getImage();

        setLayout(null); // Custom layout for positioning components

        // Initialize UI components
        initializeUI();

        // Fetch user stats from Firebase
        fetchUserStats(username);
    }

    private void initializeUI() {
        // Back button (triangle shape)
        backButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.GRAY);
                int[] xPoints = {10, 40, 40};
                int[] yPoints = {20, 5, 35};
                g2d.fillPolygon(xPoints, yPoints, 3);
            }
        };
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false); // Remove button background
        backButton.setBorderPainted(false); // Remove border
        backButton.addActionListener(e -> handleBackButtonClick());
        add(backButton);

        // User name
        userNameLabel = new JLabel("Loading...");
        userNameLabel.setFont(new Font("Arial", Font.BOLD, 96));
        userNameLabel.setForeground(Color.WHITE);
        add(userNameLabel);

        // Points
        pointsLabel = new JLabel("0 pts");
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 72));
        pointsLabel.setForeground(Color.WHITE);
        add(pointsLabel);

        // Games
        gamesLabel = new JLabel("0 games");
        gamesLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        gamesLabel.setForeground(Color.WHITE);
        add(gamesLabel);

        // Good guesses
        guessesLabel = new JLabel("0 good guesses");
        guessesLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        guessesLabel.setForeground(Color.WHITE);
        add(guessesLabel);

        // See Games button
        seeGamesButton = new JButton("See Games");
        seeGamesButton.setPreferredSize(new Dimension(200, 80));
        seeGamesButton.setBackground(Color.GRAY);
        seeGamesButton.setForeground(Color.WHITE);
        seeGamesButton.setFont(new Font("Arial", Font.PLAIN, 18));
        seeGamesButton.setFocusPainted(false);
        seeGamesButton.addActionListener(e -> handleSeeGamesButtonClick());
        add(seeGamesButton);

        // Layout adjustment listener
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                layoutComponents();
            }
        });
    }

    private void layoutComponents() {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Position the back button
        backButton.setBounds(20, panelHeight / 20 + 20, 50, 50);

        // Position the user name
        userNameLabel.setBounds(panelWidth / 10, panelHeight / 20, 800, 100);

        // Center the points label
        pointsLabel.setBounds((panelWidth - 400) / 2, panelHeight / 2 - 150, 400, 80);

        // Position games label to the left of points
        gamesLabel.setBounds((panelWidth - 400) / 2 - 200, panelHeight / 2 - 60, 200, 40);

        // Position the guesses label to the right of points
        guessesLabel.setBounds((panelWidth + 400) / 2, panelHeight / 2 - 60, 300, 40);

        // Center the See Games button lower on the Y-axis
        seeGamesButton.setBounds((panelWidth - 200) / 2, panelHeight / 2 + 150, 200, 80);
    }

    private void fetchUserStats(String username) {
        // Fetch user stats using the use case
        GetUserStatsUseCase useCase = new GetUserStatsUseCase(new UserStatsRepositoryImpl());

        useCase.getUserStats(username).thenAccept(stats -> {
            SwingUtilities.invokeLater(() -> updateUIWithStats(stats));
        }).exceptionally(throwable -> {
            SwingUtilities.invokeLater(() -> {
                userNameLabel.setText("Error");
                pointsLabel.setText("Error");
                gamesLabel.setText("Error");
                guessesLabel.setText("Error");
            });
            throwable.printStackTrace();
            return null;
        });
    }

    private void updateUIWithStats(UserStats stats) {
        // Update the UI components with user stats
        userNameLabel.setText(stats.getUser());
        pointsLabel.setText(stats.getPoints() + " pts");
        gamesLabel.setText(stats.getNumGames() + " games");
        guessesLabel.setText(stats.getGoodGuesses() + " good guesses");
    }

    private void handleSeeGamesButtonClick() {
        System.out.println("See Games button clicked!");
        // Add logic for navigating to the games page
    }

    private void handleBackButtonClick() {
        System.out.println("Back button clicked!");
        // Add logic for navigating back
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image to fill the panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public static void main(String[] args) {
        // Create a sample stats page
        JFrame frame = new JFrame("Stats Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        frame.setLocationRelativeTo(null);

        StatsPage statsPage = new StatsPage("Alice");
        frame.add(statsPage);

        frame.setVisible(true);
    }
}
