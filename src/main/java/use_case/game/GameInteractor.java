package use_case.game;

import data_access.LocationDataAccess;
import entity.GameRound;
import entity.GameStates;
import entity.PhotoLocation;
import entity.PhotoLocationFactory;
import util.DistanceCalculator;

import java.util.Random;

/**
 * This handles the main game logic.
 */
public class GameInteractor implements GameInputBoundary {

    protected GameStates gameStates;

    protected GameOutputBoundary presenter;

    protected LocationDataAccess photoAccess;

    /**
     * Make a new game interactor.
     *
     * @param photoAccess the database to access the photo
     * @param presenter   the presenter to change view
     */
    public GameInteractor(LocationDataAccess photoAccess, GameOutputBoundary presenter) {
        this.presenter = presenter;
        this.photoAccess = photoAccess;
        gameStates = new GameStates();
    }

    /**
     * Initiate the game.
     */
    public void init() {
        gameStates.clean();
        setSeed(new Random().nextLong());
        PhotoLocation p = getNextPhoto();
        GameOutputData gameOutputData = new GameOutputData(false, 0, p.getPhoto(), p.getPhotoID(), p.getLocation(), gameStates.getRounds() + 1);
        presenter.init(gameOutputData);
    }

    /**
     * Handle the user's guess
     *
     * @param input game input data
     */
    public void handleGuess(GameInputData input) {

        final int photoID = input.getPhotoID();
        final double[] target = input.getTarget();
        final double[] chosen = input.getChosen();
        final boolean isAcc = isAcceptable(target, chosen);

        GameRound round = new GameRound(photoID, target, chosen, isAcc);
        gameStates.add(round);
        gameStates.setScore(gameStates.getScore() + calculateScore(target, chosen));

        if (gameStates.getRounds() == 10) {
            endGame(round.isAcceptable());
        } else {
            PhotoLocation p = getNextPhoto();
            GameOutputData gameOutputData = new GameOutputData(round.isAcceptable(), gameStates.getScore(),
                    p.getPhoto(), p.getPhotoID(), p.getLocation(), gameStates.getRounds() + 1);
            presenter.handleGuess(gameOutputData);
        }
    }

    private int calculateScore(double[] target, double[] chosen) {
        // should change later
        return (int) (200 / (1 + DistanceCalculator.calculate(target, chosen)));
    }

    private boolean isAcceptable(double[] target, double[] chosen) {
        // should change later
        return DistanceCalculator.calculate(target, chosen) < 100;
    }

    /**
     * To get the next photo location.
     *
     * @return next photo location
     */
    protected PhotoLocation getNextPhoto() {
        PhotoLocationFactory photoFactory = new PhotoLocationFactory(photoAccess);
        PhotoLocation l = photoFactory.getRandomLocation();
        return l;
    }

    /**
     * Set the seed.
     *
     * @param seed random seed
     */
    public void setSeed(long seed) {
        photoAccess.setSeed(seed);
        System.out.println("set seed");
    }

    /**
     * To end the game.
     *
     * @param isAcceptable isAcceptable
     */
    protected void endGame(boolean isAcceptable) {
        System.out.println("game finished");
        System.out.println(gameStates.getRounds() + 1);
        GameOutputData gameOutputData = new GameOutputData(isAcceptable, gameStates.getScore(),
                null, 0, null, gameStates.getRounds() + 1);

        presenter.endGame(gameOutputData);
    }

    public void timeout(GameInputData input) {
        final int photoID = input.getPhotoID();
        final double[] target = input.getTarget();
        final double[] chosen = input.getChosen();
        final boolean isAcc = isAcceptable(target, chosen);

        GameRound round = new GameRound(photoID, target, chosen, isAcc);
        gameStates.add(round);
        gameStates.setScore(gameStates.getScore() + calculateScore(target, chosen));

        endGame(round.isAcceptable());
    }

}
