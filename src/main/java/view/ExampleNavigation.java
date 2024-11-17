package view;

import entity.DummyUserStats;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import view.pages.MainPage;
import view.pages.PageFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ExampleNavigation {

    public static void main(String[] args) {
        // Setup dummy state
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
        leaderboardViewModel.setState(new LeaderboardState(createLeaderboard()));

        AccountViewModel accountViewModel = new AccountViewModel();
        accountViewModel.setState(new AccountState(false));

        // Run UI
        final PageFrame frame = new PageFrame();
        frame.navigate(new MainPage(leaderboardViewModel, accountViewModel, frame));
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
    }

    private static List<DummyUserStats> createLeaderboard() {
        List<DummyUserStats> leaderboard = new ArrayList<>();
        leaderboard.add(new DummyUserStats("Zev", (int) (10000 * Math.random())));
        leaderboard.add(new DummyUserStats("Terrence", (int) (10000 * Math.random())));
        leaderboard.add(new DummyUserStats("Rithvik", (int) (10000 * Math.random())));
        leaderboard.add(new DummyUserStats("Chris", (int) (10000 * Math.random())));
        return leaderboard;
    }

}
