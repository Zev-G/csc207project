package view.pages;

import view.components.standard.DFrame;

public class PageFrame extends DFrame implements PageManager {

    public void navigate(Page page) {
        removeAll();
        add(page);
    }

}
