package view.pages;

import view.components.standard.DPanel;

public abstract class Page extends DPanel {

    protected final ViewManager viewManager;

    protected Page(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void init(){

    }

}
