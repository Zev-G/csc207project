package view.pages;

import view.ViewConstants;
import view.components.AppViewManager;
import view.components.standard.DLabel;
import view.components.standard.DPanel;
import view.components.standard.VerticalPanel;

import java.awt.*;

public class WaitingPage extends Page {

    private final DLabel pageTitle = new DLabel("Waiting for response from the server.");
    private final VerticalPanel titleLayout = new VerticalPanel(pageTitle);
    private final DPanel buttons = new DPanel();
    private final DPanel grid = new DPanel();

    public WaitingPage(AppViewManager app) {
        super(app.getViewManager());

        setMargin(ViewConstants.MARGIN_M);
        pageTitle.setFontSize(ViewConstants.TEXT_LL);

        // Layout
        BorderLayout layout = new BorderLayout();
        layout.setVgap(60);
        setLayout(layout);
        add(titleLayout, BorderLayout.PAGE_START);
        add(grid, BorderLayout.CENTER);
        add(buttons, BorderLayout.PAGE_END);

        grid.setMargin(0, 400, 0, 400);

        grid.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Page title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around the component
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
        grid.add(pageTitle, gbc);

        gbc.gridwidth = 1; // Single column

        buttons.setLayout(new FlowLayout());
    }

}
