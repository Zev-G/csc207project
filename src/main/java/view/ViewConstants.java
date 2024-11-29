package view;

import view.utils.FontGenerator;

import java.awt.*;

public final class ViewConstants {

    public static final int MARGIN_L = 20;
    public static final int GAP_S = 5;
    public static final int HEIGHT_SS = 40;
    public static final int TEXT_M = 30;
    public static final int TEXT_L = 60;
    public static final int TEXT_LL = 90;
    public static final FontGenerator SERIF_FONT = getFont("Serif");
    public static final FontGenerator DEFAULT_FONT = getFont("SansSerif");
    public static final int MARGIN_M = 50;

    public static FontGenerator getFont(String name) {
        return size -> new Font(name, Font.PLAIN, size);
    }
}
