package app;

import javax.swing.*;
import java.awt.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */

public class AppBuilder {

    public AppBuilder() {

    }

    public JFrame build() {
        final JFrame application = new JFrame("");

        return application;
    }
}
