package view.components;

import javax.swing.*;
import java.awt.*;

public class HorizontalPanel extends DPanel {

    public HorizontalPanel(JComponent... components) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        for (JComponent component : components) {
            add(component);
        }
    }

}
