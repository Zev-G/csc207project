package view.components;

import view.ViewConstants;

import javax.swing.*;

public class DLabel extends JLabel {

    private FontGenerator fontGenerator = ViewConstants.DEFAULT_FONT;
    private int fontSize = ViewConstants.TEXT_M;

    public DLabel() {
        this("");
    }

    public DLabel(String text) {
        super(text);
        updateFont();
    }

    private void updateFont() {
        setFont(fontGenerator.getFont(fontSize));
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        updateFont();
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontGenerator(FontGenerator fontGenerator) {
        this.fontGenerator = fontGenerator;
        updateFont();
    }

    public FontGenerator getFontGenerator() {
        return fontGenerator;
    }

}
