package use_case.stats;

/**
 * Represents input data for fetching user statistics, including the username.
 */
public class StatsInputData {

    private final String username;

    /**
     * Constructs a StatsInputData object with the specified username.
     *
     * @param username the username of the user whose statistics are to be fetched
     */
    public StatsInputData(String username) {
        this.username = username;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }
}
