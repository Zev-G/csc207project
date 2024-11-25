package view.pages;

import view.pages.Page;

public interface PageManager {

    void back();
    void forward();

    void navigate(Page page);

}
