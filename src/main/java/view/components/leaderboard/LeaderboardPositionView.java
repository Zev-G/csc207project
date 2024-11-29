package view.components.leaderboard;

import entity.DummyUserStats;
import view.components.standard.DLabel;
import view.components.standard.HorizontalPanel;

/**
 * A view component for displaying a single user's position on the leaderboard.
 */
public class LeaderboardPositionView extends HorizontalPanel {

    private final DLabel label;

    /**
     * Constructs a LeaderboardPositionView for the given user's statistics.
     *
     * @param userStats the user's statistics containing the username and points
     */
    public LeaderboardPositionView(DummyUserStats userStats) {
        label = new DLabel(userStats.getUser() + " - " + userStats.getPoints());
        add(label);
    }
}
