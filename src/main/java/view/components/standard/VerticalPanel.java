package view.components.standard;

import javax.swing.*;

/**
 * A custom vertical panel that arranges components in a vertical layout.
 */
public class VerticalPanel extends DPanel {

    /**
     * Constructs a VerticalPanel and adds the provided components arranged in a vertical layout.
     *
     * @param components the components to be added to the panel
     */
    public VerticalPanel(JComponent... components) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (JComponent component : components) {
            add(component);
        }
    }
}
