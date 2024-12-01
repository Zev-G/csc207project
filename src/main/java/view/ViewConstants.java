package view;

import view.utils.FontGenerator;

import java.awt.*;

/**
 * ViewConstants contains static constants for various UI-related values like margins, gaps, font sizes, and predefined fonts.
 * These constants are used throughout the application for consistent UI styling.
 */
public final class ViewConstants {

    /**
     * Margin for large space between components.
     */
    public static final int MARGIN_L = 20;

    /**
     * Small gap size between UI elements.
     */
    public static final int GAP_S = 5;

    /**
     * Small height for UI components.
     */
    public static final int HEIGHT_SS = 40;

    /**
     * Font size for medium text.
     */
    public static final int TEXT_M = 30;

    /**
     * Font size for large text.
     */
    public static final int TEXT_L = 60;

    /**
     * Font size for extra-large text.
     */
    public static final int TEXT_LL = 90;

    /**
     * Serif font generator, used for generating fonts with the "Serif" style.
     */
    public static final FontGenerator SERIF_FONT = getFont("Serif");

    /**
     * Default font generator, used for generating fonts with the "SansSerif" style.
     */
    public static final FontGenerator DEFAULT_FONT = getFont("SansSerif");

    /**
     * Medium margin size used for consistent spacing.
     */
    public static final int MARGIN_M = 50;

    /**
     * Utility method to get a font generator for a given font name.
     * @param name The name of the font (e.g., "Serif" or "SansSerif").
     * @return A FontGenerator that generates fonts of the specified type.
     */
    public static FontGenerator getFont(String name) {
        return size -> new Font(name, Font.PLAIN, size);
    }
}
