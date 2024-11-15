package view;

import view.components.DLabel;
import view.components.DPanel;
import view.components.HorizontalPanel;
import view.components.SerifLabel;

import javax.swing.*;

public class MainPanel extends DPanel {

    // UI Fields
    private final DLabel uoftText = new SerifLabel("UofT");
    private final DLabel gameText = new DLabel("Guessr");
    private final JPanel titlePanel = new HorizontalPanel(uoftText, gameText);

    public MainPanel() {
        // Configure components
        uoftText.setFontSize(ViewConstants.TEXT_LL);
        gameText.setFontSize(ViewConstants.TEXT_LL);

        setMargin(ViewConstants.MARGIN_M);

        // Add components
        add(titlePanel);
    }

}
