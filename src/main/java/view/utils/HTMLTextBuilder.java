package view.utils;

/**
 * HTMLTextBuilder is a utility class for building HTML-formatted text with optional styling.
 * It supports adding text, line breaks, and centering the content.
 */
public class HTMLTextBuilder {

    /**
     * Flag indicating whether the text should be centered.
     */
    private boolean centered;

    /**
     * The StringBuilder to accumulate the text content.
     */
    private final StringBuilder text = new StringBuilder();

    /**
     * Default constructor for initializing the HTMLTextBuilder.
     */
    public HTMLTextBuilder() {

    }

    /**
     * Adds the specified text to the builder.
     * @param text The text to add.
     * @return The current HTMLTextBuilder instance for method chaining.
     */
    public HTMLTextBuilder addText(String text) {
        this.text.append(text);
        return this;
    }

    /**
     * Adds a line break (<br>) to the builder.
     * @return The current HTMLTextBuilder instance for method chaining.
     */
    public HTMLTextBuilder addLineBreak() {
        this.text.append("<br>");
        return this;
    }

    /**
     * Builds and returns the complete HTML string.
     * @return The HTML-formatted text as a string.
     */
    public String build() {
        return "<html><div style='" + (centered ? "text-align: center; " : "") + "'>" + text.toString() + "</div></html>";
    }

    /**
     * Centers the text content by setting the appropriate CSS style.
     * @return The current HTMLTextBuilder instance for method chaining.
     */
    public HTMLTextBuilder center() {
        this.centered = true;
        return this;
    }
}
