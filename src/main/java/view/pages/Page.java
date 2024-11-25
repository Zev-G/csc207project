package view.pages;

import view.components.standard.DPanel;

public abstract class Page extends DPanel {

    protected final PageManager pageManager;

    protected Page(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    public void init(){

    }

}
