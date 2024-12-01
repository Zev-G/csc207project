package view.pages;

import view.components.standard.DFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * PageFrame is a container for managing navigation between pages.
 * It extends DFrame and implements the PageManager interface.
 * Supports back and forward navigation between pages using stacks.
 */
public class PageFrame extends DFrame implements PageManager {

    /**
     * Stack to hold the history of pages for back navigation.
     */
    private Stack<Page> history = new Stack<>();

    /**
     * Stack to hold the pages for forward navigation.
     */
    private Stack<Page> forwardStack = new Stack<>();

    /**
     * The current page displayed in the frame.
     */
    private Page currentPage = null;

    /**
     * Navigates back to the previous page, if available.
     * The current page is saved to the forward stack before navigating back.
     */
    @Override
    public void back() {
        if (!history.isEmpty()) {
            if (currentPage != null) {
                forwardStack.push(currentPage); // Save the current page to the forward stack
            }
            Page previousPage = history.pop(); // Get the previous page
            setPage(previousPage);
        }
    }

    /**
     * Navigates forward to the next page, if available.
     * The current page is saved to the history stack before navigating forward.
     */
    @Override
    public void forward() {
        if (!forwardStack.isEmpty()) {
            if (currentPage != null) {
                history.push(currentPage); // Save the current page to the history stack
            }
            Page nextPage = forwardStack.pop(); // Get the next page
            setPage(nextPage);
        }
    }

    /**
     * Navigates to the specified page.
     * The current page is saved to history, and the forward stack is cleared.
     * @param page The page to navigate to.
     */
    public void navigate(Page page) {
        if (currentPage != null) {
            history.push(currentPage); // Save the current page to history before navigating
        }
        forwardStack.clear();          // Clear forward stack on new navigation
        setPage(page);                 // Set the new page
    }

    /**
     * Sets the given page as the current page.
     * Removes the current page if one exists and adds the new page to the frame.
     * @param page The page to be set as current.
     */
    private void setPage(Page page) {
        if (currentPage != null) remove(currentPage);
        currentPage = page;
        page.init();
        add(currentPage);              // Add the new page to the frame
        revalidate();                  // Refresh the frame layout
        repaint();                     // Redraw the frame
    }
}
