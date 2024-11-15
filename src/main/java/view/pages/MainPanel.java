package view.pages;

import interface_adapter.leaderboard.LeaderboardViewModel;
import view.ViewConstants;
import view.components.leaderboard.LeaderboardView;
import view.components.standard.*;
import view.utils.HTMLTextBuilder;

import javax.swing.*;

public class MainPanel extends DPanel {

    // UI Fields
    private final DLabel uoftText = new SerifLabel("UofT");
    private final DLabel gameText = new DLabel("Guessr");
    private final DLabel subtitleText = new DLabel(
            new HTMLTextBuilder()
                    .addText("Test your knowledge of UofT by guessing")
                    .addLineBreak()
                    .addText("where these photos were taken")
                    .center().build());
    private final DPanel titlePanel = new HorizontalPanel(uoftText, gameText);
    private final DPanel subtitlePanel = new HorizontalPanel(subtitleText);
    private final LeaderboardView leaderboard;
    private final VerticalPanel layout = new VerticalPanel(titlePanel, subtitlePanel);

    public MainPanel(LeaderboardViewModel lbvm) {
        // Create objects
        leaderboard = new LeaderboardView(lbvm);

        // Configure components
        uoftText.setFontSize(ViewConstants.TEXT_LL);
        gameText.setFontSize(ViewConstants.TEXT_LL);

        setMargin(ViewConstants.MARGIN_M);
        subtitleText.setHorizontalAlignment(SwingConstants.CENTER);

        // Add components
        layout.add(leaderboard);

        add(layout);
    }

}
