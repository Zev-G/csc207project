package view;

import interface_adapter.ViewModel;

public interface View<T> {

    void loadState(T state);
    ViewModel<T> getViewModel();

    default void loadCurrentState() {
        loadState(getViewModel().getState());
    }

}
