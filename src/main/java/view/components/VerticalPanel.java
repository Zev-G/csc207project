package view.components;

import javax.swing.*;
import java.awt.*;

public class VerticalPanel extends DPanel {

        public VerticalPanel(JComponent... components) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            for (JComponent component : components) {
                add(component);
            }
        }

}
