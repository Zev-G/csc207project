package view;

import view.components.*;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends DPanel {

    // UI Fields
    private final DLabel uoftText = new SerifLabel("UofT");
    private final DLabel gameText = new DLabel("Guessr");
    private final DLabel subtitleText = new DLabel("<html><div style='text-align: center;'>Test your knowledge of UofT by guessing<br>where these photos were taken</div></html>");
    private final DPanel titlePanel = new HorizontalPanel(uoftText, gameText);
    private final DPanel subtitlePanel = new HorizontalPanel(subtitleText);
    private final VerticalPanel layout = new VerticalPanel(titlePanel, subtitlePanel);

    public MainPanel() {
        // Configure components
        uoftText.setFontSize(ViewConstants.TEXT_LL);
        gameText.setFontSize(ViewConstants.TEXT_LL);

        setMargin(ViewConstants.MARGIN_M);
        subtitleText.setHorizontalAlignment(SwingConstants.CENTER);

        // Add components
        add(layout);
    }

}
