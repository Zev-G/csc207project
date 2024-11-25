package use_case.game;

import data_access.LocationDataAccess;
import entity.GameRound;
import entity.GameStates;
import entity.PhotoLocation;
import entity.PhotoLocationFactory;
import util.DistanceCalculator;

import java.util.Random;

public class GameInteractor implements GameInputBoundary {

    protected GameStates gameStates;

    protected GameOutputBoundary presenter;

    protected LocationDataAccess photoAccess;

    public GameInteractor(LocationDataAccess photoAccess, GameOutputBoundary presenter) {
        this.presenter = presenter;
        this.photoAccess = photoAccess;
        gameStates = new GameStates();
    }

    public void init() {
        gameStates.clean();
        setSeed(new Random().nextLong());
        PhotoLocation p = getNextPhoto();
        GameOutputData gameOutputData = new GameOutputData(false, 0, p.getPhoto(), p.getPhotoID(), p.getLocation(), gameStates.getRounds());
        presenter.init(gameOutputData);
    }

    public void handleGuess(GameInputData input) {

        final int photoID = input.getPhotoID();
        final double[] target = input.getTarget();
        final double[] chosen = input.getChosen();
        final boolean isAcc = isAcceptable(target, chosen);

        GameRound round = new GameRound(photoID, target, chosen, isAcc);
        gameStates.add(round);
        gameStates.setScore(gameStates.getScore() + calculateScore(target, chosen));

        if (gameStates.getRounds() == 11) {
            endGame(round.isAcceptable());
        }

        PhotoLocation p = getNextPhoto();
        GameOutputData gameOutputData = new GameOutputData(round.isAcceptable(), gameStates.getScore(),
                p.getPhoto(), p.getPhotoID(), p.getLocation(), gameStates.getRounds());
        presenter.handleGuess(gameOutputData);
    }

    private int calculateScore(double[] target, double[] chosen) {
        // should change later
        return (int) (200 / DistanceCalculator.calculate(target, chosen));
    }

    private boolean isAcceptable(double[] target, double[] chosen) {
        // should change later
        return DistanceCalculator.calculate(target, chosen) < 100;
    }

    protected PhotoLocation getNextPhoto() {
        PhotoLocationFactory photoFactory = new PhotoLocationFactory(photoAccess);
        PhotoLocation l = photoFactory.getRandomLocation();
        return l;
    }

    public void setSeed(long seed) {
        photoAccess.setSeed(seed);
    }

    protected void endGame(boolean isAcceptable) {
        System.out.println("game finished");

        GameOutputData gameOutputData = new GameOutputData(isAcceptable, gameStates.getScore(),
                null, 0, null, gameStates.getRounds());

        presenter.endGame(gameOutputData);
    }

}
