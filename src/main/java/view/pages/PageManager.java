package view.pages;

import view.pages.Page;

/**
 * Interface defining the methods for managing page navigation.
 * Implementing classes should provide functionality for back, forward, and navigate operations.
 */
public interface PageManager {

    /**
     * Navigates back to the previous page, if available.
     */
    void back();

    /**
     * Navigates forward to the next page, if available.
     */
    void forward();

    /**
     * Navigates to the specified page.
     * @param page The page to navigate to.
     */
    void navigate(Page page);

}
