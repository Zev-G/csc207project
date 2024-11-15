package view.components;

import javax.swing.*;
import java.awt.*;

public class HorizontalPanel extends DPanel {

    public HorizontalPanel(JComponent... components) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        for (JComponent component : components) {
            add(component);
        }
    }

}
