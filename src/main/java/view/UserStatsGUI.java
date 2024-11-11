package view;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;

public class UserStatsGUI extends JFrame {
    private ArrowButton guessButton;
    private JLabel crownLabel;
    private JLabel numberLabel;
    private JLabel statsLabel;

    public UserStatsGUI() {
        setTitle("User Stats GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Load background image and set as background label
        JLabel backgroundLabel = new JLabel();
        setBackgroundImage(backgroundLabel, "GUIimages/bg.jpg");
        setContentPane(backgroundLabel);
        backgroundLabel.setLayout(new BorderLayout());

        // Top panel with custom arrow-shaped "Guess" button pointing left
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false); // Transparent to show background
        guessButton = new ArrowButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 24));
        guessButton.setBackground(new Color(255, 215, 0)); // Set background color of the arrow
        guessButton.setForeground(Color.BLACK); // Set text color
        guessButton.setPreferredSize(new Dimension(100, 80));
        topPanel.add(guessButton, BorderLayout.WEST);

        backgroundLabel.add(topPanel, BorderLayout.NORTH);

        // Center panel for crown and "17"
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        // Create number and crown container panel
        JPanel numberAndCrownPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        numberAndCrownPanel.setOpaque(false);

        crownLabel = new JLabel();
        loadCrownImage();
        numberAndCrownPanel.add(crownLabel);

        // Large "17" label next to crown
        numberLabel = new JLabel("17");
        numberLabel.setFont(new Font("Arial", Font.BOLD, 72));
        numberLabel.setForeground(Color.WHITE);
        numberAndCrownPanel.add(numberLabel);

        centerPanel.add(numberAndCrownPanel);

        // Stats label below crown and "17"
        statsLabel = new JLabel("65% Accuracy | 23 Games Played | 87% Best Accuracy");
        statsLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        statsLabel.setForeground(Color.WHITE);
        statsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Customize font size for middle stat
        String statsHtml = "<html><div style='text-align: center;'>65% Accuracy | "
                + "<span style='font-size:28px;'>23 Games Played</span> | 87% Best Accuracy</div></html>";
        statsLabel.setText(statsHtml);

        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(statsLabel);
        backgroundLabel.add(centerPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setBackgroundImage(JLabel backgroundLabel, String imagePath) {
        try {
            ImageIcon bgIcon = new ImageIcon(ImageIO.read(getClass().getResource("/" + imagePath)));
            backgroundLabel.setIcon(bgIcon);
        } catch (IOException e) {
            System.out.println("Background image not found: " + e.getMessage());
        }
    }

    private void loadCrownImage() {
        try {
            ImageIcon crownIcon = new ImageIcon(ImageIO.read(getClass().getResource("/GUIimages/crown.png")));
            crownLabel.setIcon(ImageScaler.getScaledImageIcon(crownIcon, 100, 100));
            crownLabel.setHorizontalAlignment(SwingConstants.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
            crownLabel.setText("Crown image not found");
        }
    }

    public static void main(String[] args) {
        new UserStatsGUI();
    }
}
