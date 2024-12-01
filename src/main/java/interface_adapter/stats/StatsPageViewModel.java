package interface_adapter.stats;

import interface_adapter.ViewModel;

/**
 * ViewModel for the StatsPage, extending the base ViewModel class.
 * Manages the state and logic specific to the statistics page.
 */
public class StatsPageViewModel extends ViewModel<StatsPageState> {

    /**
     * Constructs a StatsPageViewModel and initializes it with the "statsPage" identifier.
     */
    public StatsPageViewModel() {
        super("statsPage");
    }
}
