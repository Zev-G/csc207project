package view.components.standard;

import view.ViewConstants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DPanel extends JPanel {

    public DPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
        init();
    }

    public DPanel(LayoutManager layout) {
        super(layout);
        init();
    }

    public DPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        init();
    }

    public DPanel() {
        init();
    }

    private void init() {

    }

    /**
     * Sets the margin
     * @param t top
     * @param r right
     * @param b bottom
     * @param l left
     */
    public void setMargin(int t, int r, int b, int l) {
        setBorder(new EmptyBorder(t, r, b, l));
    }

    /**
     * Sets the margin
     * @param margin the margin
     */
    public void setMargin(int margin) {
        setMargin(margin, margin, margin, margin);
    }

}
