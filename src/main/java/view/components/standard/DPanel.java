package view.components.standard;

import view.ViewConstants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A custom JPanel with additional functionality for setting margins.
 */
public class DPanel extends JPanel {

    /**
     * Constructs a DPanel with the specified layout and buffering configuration.
     *
     * @param layout          the layout manager for the panel
     * @param isDoubleBuffered whether the panel uses double buffering
     */
    public DPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
        init();
    }

    /**
     * Constructs a DPanel with the specified layout.
     *
     * @param layout the layout manager for the panel
     */
    public DPanel(LayoutManager layout) {
        super(layout);
        init();
    }

    /**
     * Constructs a DPanel with the specified buffering configuration.
     *
     * @param isDoubleBuffered whether the panel uses double buffering
     */
    public DPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        init();
    }

    /**
     * Constructs a DPanel with default settings.
     */
    public DPanel() {
        init();
    }

    /**
     * Initializes the panel. Reserved for future customization.
     */
    private void init() {
        // Initialization logic (currently empty)
    }

    /**
     * Sets the margin for the panel.
     *
     * @param t top margin
     * @param r right margin
     * @param b bottom margin
     * @param l left margin
     */
    public void setMargin(int t, int r, int b, int l) {
        setBorder(new EmptyBorder(t, r, b, l));
    }

    /**
     * Sets a uniform margin for all sides of the panel.
     *
     * @param margin the margin size for all sides
     */
    public void setMargin(int margin) {
        setMargin(margin, margin, margin, margin);
    }
}
