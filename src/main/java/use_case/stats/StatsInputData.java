package use_case.stats;

public class StatsInputData {
    private final String username;

    public StatsInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
