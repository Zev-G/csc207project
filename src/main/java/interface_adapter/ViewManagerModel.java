package interface_adapter;

/**
 * Manages the current active view. The initial state is an empty string ("").
 */
public class ViewManagerModel extends ViewModel<String> {

    /**
     * Constructs a ViewManagerModel with the default initial state.
     */
    public ViewManagerModel() {
        super("view manager");
        this.setState("");
    }
}
