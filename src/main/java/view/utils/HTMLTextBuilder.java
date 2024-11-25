package view.utils;

public class HTMLTextBuilder {

    private boolean centered;
    private final StringBuilder text = new StringBuilder();

    public HTMLTextBuilder() {

    }

    public HTMLTextBuilder addText(String text) {
        this.text.append(text);
        return this;
    }

    public HTMLTextBuilder addLineBreak() {
        this.text.append("<br>");
        return this;
    }

    public String build() {
        return "<html><div style='" + (centered ? "text-align: center; " : "") + "'>" + text.toString() + "</div></html>";
    }

    public HTMLTextBuilder center() {
        this.centered = true;
        return this;
    }
}
