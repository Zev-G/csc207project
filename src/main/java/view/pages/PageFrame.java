package view.pages;

import view.components.standard.DFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class PageFrame extends DFrame implements PageManager {

    private Stack<Page> history = new Stack<>();
    private Stack<Page> forwardStack = new Stack<>();

    private Page currentPage = null;

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

    public void navigate(Page page) {
        if (currentPage != null) {
            history.push(currentPage); // Save the current page to history before navigating
        }
        forwardStack.clear();          // Clear forward stack on new navigation
        setPage(page);                 // Set the new page
    }

    private void setPage(Page page) {
        if (currentPage != null) remove(currentPage);
        currentPage = page;
        add(currentPage);              // Add the new page to the frame
        revalidate();                  // Refresh the frame layout
        repaint();                     // Redraw the frame
    }
}
