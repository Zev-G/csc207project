package view.components.leaderboard;

import entity.DummyUserStats;
import view.components.standard.DLabel;
import view.components.standard.HorizontalPanel;

import java.awt.*;

public class LeaderboardPositionView extends HorizontalPanel {

    private final DLabel label;

    public LeaderboardPositionView(DummyUserStats userStats) {
        label = new DLabel(userStats.getUser() + " - " + userStats.getPoints());
        add(label);
    }
}
