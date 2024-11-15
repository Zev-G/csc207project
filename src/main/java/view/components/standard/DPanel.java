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
     * @param margin the margin
     */
    public void setMargin(int margin) {
        setBorder(new EmptyBorder(margin, margin, margin, margin));
    }

}
