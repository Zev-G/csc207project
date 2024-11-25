package view;

import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import view.pages.AccountPage;
import view.pages.MainPage;
import view.pages.PageFrame;

import javax.swing.*;

public class ExampleAccount {

    public static void main(String[] args) {
        // Setup dummy state
        AccountViewModel accountViewModel = new AccountViewModel();
        accountViewModel.setState(new AccountState(true, "Zev", "godfreyzev@gmail.com", "1234"));

        // Run UI
        final PageFrame frame = new PageFrame();
        frame.navigate(new AccountPage(accountViewModel, frame));
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
    }

}
