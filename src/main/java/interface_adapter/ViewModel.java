package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Generic ViewModel for managing state and notifying listeners of property changes.
 *
 * @param <T> The type of the state object managed by the ViewModel.
 */
public class ViewModel<T> {

    private final String viewName;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private T state;

    /**
     * Constructs a ViewModel with a given view name.
     *
     * @param viewName the name of the view managed by this ViewModel
     */
    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    /**
     * Returns the name of the view.
     *
     * @return the view name
     */
    public String getViewName() {
        return this.viewName;
    }

    /**
     * Returns the current state of the ViewModel.
     *
     * @return the current state
     */
    public T getState() {
        return this.state;
    }

    /**
     * Updates the state and notifies listeners of the change.
     *
     * @param state the new state
     */
    public void setState(T state) {
        this.state = state;
        firePropertyChanged();
    }

    /**
     * Notifies listeners of a state change using the default property name ("state").
     */
    public void firePropertyChanged() {
        this.support.firePropertyChange("state", null, this.state);
    }

    /**
     * Notifies listeners of a state change with a specified property name.
     *
     * @param propertyName the name of the changed property
     */
    public void firePropertyChanged(String propertyName) {
        this.support.firePropertyChange(propertyName, null, this.state);
    }

    /**
     * Adds a listener to be notified of property changes.
     *
     * @param listener the listener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }
}
