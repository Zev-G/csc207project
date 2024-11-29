package view.pages;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import view.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

public class ViewManager extends PageFrame implements View<String> {

    protected ViewManagerModel viewManagerModel;

    private Map<String, Page> pageMap = new HashMap<>();

    public ViewManager(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        viewManagerModel.addPropertyChangeListener(evt -> loadCurrentState());
    }

    public void add(String name, Page page) {
        pageMap.put(name, page);
    }

    public void navigate(String name) {
        navigate(pageMap.get(name));
    }

    @Override
    public void loadState(String state) {
        navigate(pageMap.get(viewManagerModel.getState()));
    }

    @Override
    public ViewModel<String> getViewModel() {
        return viewManagerModel;
    }
}
