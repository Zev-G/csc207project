package view.pages;

import view.components.standard.DPanel;

/**
 * Abstract class representing a page in the view.
 * Inherits from DPanel and is used as a base for specific page implementations.
 */
public abstract class Page extends DPanel {

    /**
     * The ViewManager that controls the page's view.
     */
    protected final ViewManager viewManager;

    /**
     * Constructor that initializes the page with a ViewManager.
     * @param viewManager The ViewManager responsible for this page.
     */
    protected Page(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    /**
     * Initializes the page.
     * This method is intended to be overridden in subclasses for custom initialization.
     */
    public void init(){

    }

}
