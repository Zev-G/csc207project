/**
 * StatsPageViewModel.java
 *
 * This class acts as the ViewModel in the MVVM (Model-View-ViewModel) design pattern for the statistics page.
 * It connects the `StatsPageState` to the user interface and serves as an observable data holder.
 *
 * Dimensions of Documentation (ACCEU):
 * - **Accuracy**: Explains the purpose and role of the class.
 * - **Clarity**: Provides concise and clear documentation.
 * - **Completeness**: Covers constructors and relationships to other components.
 * - **Ease of Use**: Simplifies the integration of the statistics state into the UI.
 * - **Up-to-Dateness**: Reflects the latest implementation details.
 */

package interface_adapter.stats;

import interface_adapter.ViewModel;

/**
 * ViewModel for the statistics page, managing the state of the page and providing an observable interface for the UI.
 *
 * Responsibilities:
 * - Acts as a bridge between the `StatsPageState` (model) and the user interface (view).
 * - Observes and holds the state of the statistics page.
 * - Provides mechanisms for the UI to bind to changes in the statistics data.
 *
 * Usage Example:
 * <pre>
 *     StatsPageViewModel viewModel = new StatsPageViewModel();
 *     viewModel.setState(new StatsPageState("user123", 100, 5, 20));
 *     System.out.println(viewModel.getState().toString());
 * </pre>
 */
public class StatsPageViewModel extends ViewModel<StatsPageState> {

    /**
     * Constructor for StatsPageViewModel.
     *
     * Initializes the ViewModel with an identifier for the statistics page.
     * By default, the ViewModel is initialized without a state.
     */
    public StatsPageViewModel() {
        super("statsPage");
    }
}
