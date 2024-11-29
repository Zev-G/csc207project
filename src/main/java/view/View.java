package view;

import interface_adapter.ViewModel;

/**
 * View is a generic interface for views that display and manage state.
 * It is parameterized with a type T representing the state.
 */
public interface View<T> {

    /**
     * Loads the given state into the view.
     * @param state The state to load.
     */
    void loadState(T state);

    /**
     * Returns the ViewModel associated with the view.
     * @return The ViewModel for managing the state.
     */
    ViewModel<T> getViewModel();

    /**
     * Loads the current state from the ViewModel and updates the view.
     * This is a default implementation that calls loadState with the current state from the ViewModel.
     */
    default void loadCurrentState() {
        loadState(getViewModel().getState());
    }

}
