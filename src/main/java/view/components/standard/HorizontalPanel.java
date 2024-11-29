package view.components.standard;

import javax.swing.*;

/**
 * A custom horizontal panel that arranges components in a horizontal layout.
 */
public class HorizontalPanel extends DPanel {

    /**
     * Constructs a HorizontalPanel and adds the provided components arranged in a horizontal layout.
     *
     * @param components the components to be added to the panel
     */
    public HorizontalPanel(JComponent... components) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        for (JComponent component : components) {
            add(component);
        }
    }
}
