package interface_adapter.game;

import interface_adapter.ViewModel;

/**
 * ViewModel for the game summary page.
 * Manages the state using {@link GameSummaryPageState}.
 *
 * @see ViewModel
 * @see GameSummaryPageState
 */
public class GameSummaryPageViewModel extends ViewModel<GameSummaryPageState> {

    /**
     * Initializes the ViewModel with the identifier "gameSummaryPage".
     */
    public GameSummaryPageViewModel() {
        super("gameSummaryPage");
    }
}
