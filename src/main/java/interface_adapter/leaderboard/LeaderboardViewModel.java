package interface_adapter.leaderboard;

import interface_adapter.ViewModel;

/**
 * Represents the ViewModel for the leaderboard component.
 * This class manages the state of the leaderboard and extends the generic {@link ViewModel} class.
 */
public class LeaderboardViewModel extends ViewModel<LeaderboardState> {

    /**
     * Constructs a new {@code LeaderboardViewModel}.
     * The associated state is identified by the key "leaderboard".
     */
    public LeaderboardViewModel() {
        super("leaderboard");
    }
}
