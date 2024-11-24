package view.pages;

import view.components.standard.DFrame;

public class PageFrame extends DFrame implements PageManager {

    private Page currentPage = null;

    public void navigate(Page page) {
        if (currentPage != null) {
            remove(currentPage);
        }
        currentPage = page;
        add(page);
        page.init();
        paintAll(getGraphics());
    }

}
