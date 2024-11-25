package view.pages;

import interface_adapter.ViewManagerModel;
import view.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

public class ViewManager extends PageFrame {

    private ViewManagerModel viewManagerModel;

    private Map<String, Page> pageMap = new HashMap<>();

    public ViewManager(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        viewManagerModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                navigate(pageMap.get(viewManagerModel.getState()));
            }
        });
    }

    public void add(String name, Page page) {
        pageMap.put(name, page);
    }

    public void navigate(String name) {
        navigate(pageMap.get(name));
    }
}
