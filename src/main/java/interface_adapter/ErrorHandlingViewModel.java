package interface_adapter;

public class ErrorHandlingViewModel extends ViewModel<String> {
    /**
     * Constructs a ViewModel with a given view name.
     *
     * @param viewName the name of the view managed by this ViewModel
     */
    public ErrorHandlingViewModel(String viewName) {
        super(viewName);
    }
}
