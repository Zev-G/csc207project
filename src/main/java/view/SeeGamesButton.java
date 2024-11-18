package view.components.game;

public class SeeGamesButton extends GuessButton {

    public SeeGamesButton() {
        super(); // Reuse the GuessButton's setup
        getButton().setText("See Games"); // Change the label to "See Games"
    }
}
