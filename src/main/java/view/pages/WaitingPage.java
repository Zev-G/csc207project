package view.pages;

import view.ViewConstants;
import view.components.AppViewManager;
import view.components.standard.DLabel;
import view.components.standard.DPanel;
import view.components.standard.VerticalPanel;

import java.awt.*;

/**
 * WaitingPage displays a message indicating the system is waiting for a response from the server.
 * It extends Page and uses a custom layout with a title, grid, and buttons panel.
 */
public class WaitingPage extends Page {

    /**
     * The label displaying the waiting message.
     */
    private final DLabel pageTitle = new DLabel("Waiting for response from the server.");

    /**
     * The panel that holds the title of the page.
     */
    private final VerticalPanel titleLayout = new VerticalPanel(pageTitle);

    /**
     * The panel containing buttons (currently empty).
     */
    private final DPanel buttons = new DPanel();

    /**
     * The grid panel for laying out components.
     */
    private final DPanel grid = new DPanel();

    /**
     * Constructor for initializing the WaitingPage with components and layout.
     * @param app The AppViewManager that provides the necessary view manager.
     */
    public WaitingPage(AppViewManager app) {
        super(app.getViewManager());

        setMargin(ViewConstants.MARGIN_M);
        pageTitle.setFontSize(ViewConstants.TEXT_LL);

        // Layout configuration
        BorderLayout layout = new BorderLayout();
        layout.setVgap(60); // Vertical gap between components
        setLayout(layout);

        // Add components to the page
        add(titleLayout, BorderLayout.PAGE_START);
        add(grid, BorderLayout.CENTER);
        add(buttons, BorderLayout.PAGE_END);

        // Configure grid layout
        grid.setMargin(0, 400, 0, 400); // Set grid margins
        grid.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configure grid for page title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around the component
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
        grid.add(pageTitle, gbc);

        gbc.gridwidth = 1; // Reset to single column for future components

        // Set layout for buttons panel
        buttons.setLayout(new FlowLayout());
    }
}
