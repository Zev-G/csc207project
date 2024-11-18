package entity;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private int gamesPlayed;
    private int points;
    private int correctGuesses;

    /**
     * Constructs a new CommonUser with the given name and password.
     * Initializes gamesPlayed, points, and correctGuesses to 0.
     *
     * @param name     the user's name
     * @param password the user's password
     */
    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.gamesPlayed = 0; // Default value
        this.points = 0;      // Default value
        this.correctGuesses = 0; // Default value
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Gets the number of games played.
     * @return the number of games played.
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Updates the number of games played.
     * @param gamesPlayed the new number of games played.
     */
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    /**
     * Gets the user's total points.
     * @return the total points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Updates the user's total points.
     * @param points the new total points.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Gets the number of correct guesses.
     * @return the number of correct guesses.
     */
    public int getCorrectGuesses() {
        return correctGuesses;
    }

    /**
     * Updates the number of correct guesses.
     * @param correctGuesses the new number of correct guesses.
     */
    public void setCorrectGuesses(int correctGuesses) {
        this.correctGuesses = correctGuesses;
    }
}
