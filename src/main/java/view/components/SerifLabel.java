package view.components;

import view.ViewConstants;

public class SerifLabel extends DLabel {

    public SerifLabel() {
        this("");
    }

    public SerifLabel(String text) {
        super(text);
        setFontGenerator(ViewConstants.SERIF_FONT);
    }
}
