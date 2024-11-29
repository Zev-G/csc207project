package view.pages;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import view.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

/**
 * ViewManager is responsible for managing navigation between different pages.
 * It extends PageFrame and implements the View interface for managing the state.
 */
public class ViewManager extends PageFrame implements View<String> {

    /**
     * The ViewManagerModel that provides the state for the view manager.
     */
    protected ViewManagerModel viewManagerModel;

    /**
     * A map that holds the pages, keyed by their names.
     */
    private Map<String, Page> pageMap = new HashMap<>();

    /**
     * Constructor that initializes the ViewManager with the provided ViewManagerModel.
     * Registers a property change listener to update the state whenever it changes.
     * @param viewManagerModel The ViewManagerModel that manages the state.
     */
    public ViewManager(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        viewManagerModel.addPropertyChangeListener(evt -> loadCurrentState());
    }

    /**
     * Adds a page to the ViewManager with the specified name.
     * @param name The name of the page.
     * @param page The page to be added.
     */
    public void add(String name, Page page) {
        pageMap.put(name, page);
    }

    /**
     * Navigates to the page associated with the specified name.
     * @param name The name of the page to navigate to.
     */
    public void navigate(String name) {
        navigate(pageMap.get(name));
    }

    /**
     * Loads the state of the ViewManager and navigates to the corresponding page.
     * @param state The current state to load.
     */
    @Override
    public void loadState(String state) {
        navigate(pageMap.get(viewManagerModel.getState()));
    }

    /**
     * Returns the ViewModel associated with the ViewManager.
     * @return The ViewModel for managing the state.
     */
    @Override
    public ViewModel<String> getViewModel() {
        return viewManagerModel;
    }
}
