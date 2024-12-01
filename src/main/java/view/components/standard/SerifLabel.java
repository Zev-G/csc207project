package view.components.standard;

import view.ViewConstants;

/**
 * A custom label with a serif font style.
 */
public class SerifLabel extends DLabel {

    /**
     * Constructs a SerifLabel with no initial text.
     */
    public SerifLabel() {
        this("");
    }

    /**
     * Constructs a SerifLabel with the specified initial text.
     *
     * @param text the text to display in the label
     */
    public SerifLabel(String text) {
        super(text);
        setFontGenerator(ViewConstants.SERIF_FONT);
    }
}
