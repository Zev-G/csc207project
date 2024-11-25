package view.pages;

import view.components.standard.DPanel;

import javax.swing.*;

public abstract class Page extends DPanel {

    protected final PageManager pageManager;

    protected Page(PageManager pageManager) {
        this.pageManager = pageManager;
    }
}
