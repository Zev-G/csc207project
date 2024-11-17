package entity;

import org.jetbrains.annotations.NotNull;

public class DummyUserStats implements Comparable<DummyUserStats> {

    private final String user;
    private int points;

    public DummyUserStats(String user, int points) {
        this.user = user;
        this.points = points;
    }

    public String getUser() {
        return user;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public int compareTo(@NotNull DummyUserStats o) {
        return this.points - o.points;
    }
}
