package view.utils;

import java.awt.*;

/**
 * FontGenerator is an interface that defines a method for generating fonts of a specified size.
 */
public interface FontGenerator {

    /**
     * Returns a Font object with the specified size.
     * @param size The size of the font.
     * @return A Font object with the given size.
     */
    Font getFont(int size);

}
