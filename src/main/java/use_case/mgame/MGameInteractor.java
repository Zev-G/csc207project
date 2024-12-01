package use_case.mgame;

import use_case.dataAccessInterface.LocationDataAccess;
import entity.PhotoLocation;
import use_case.game.GameInteractor;
import use_case.game.GameOutputData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Multiplayer interactor.
 */
public class MGameInteractor extends GameInteractor implements MGameInputBoundary {

    private Socket socket;

    /**
     * To make a multiplayer interactor.
     *
     * @param photoAccess photo database
     * @param presenter   the presenter
     */
    public MGameInteractor(LocationDataAccess photoAccess, MGameOutputBoundary presenter) {
        super(photoAccess, presenter);
    }

    /**
     * To start a multiplayer game.
     *
     * @param seed   the seed to generate images
     * @param socket the socket to communicate with the server
     */
    @Override
    public void startMGame(long seed, Socket socket) {
        this.socket = socket;
        gameStates.clean();
        setSeed(seed);
        System.out.println(seed);
        PhotoLocation p = getNextPhoto();
        GameOutputData gameOutputData = new GameOutputData(false, 0, p.getPhoto(), p.getPhotoID(), p.getLocation(), gameStates.getRounds() + 1);
        presenter.init(gameOutputData);
    }

    /**
     * To end the game.
     *
     * @param isAcceptable isAcceptable
     */
    @Override
    protected void endGame(boolean isAcceptable) {

        System.out.println("game finished ENDDDD");

        ((MGameOutputBoundary) presenter).waitForResponse();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int oppScore = 0;

                boolean error = false;

                try {
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    out.writeUTF(String.valueOf(gameStates.getScore()));
                    out.flush();
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    oppScore = Integer.parseInt(in.readUTF());

                } catch (IOException | NumberFormatException e) {
                    error = true;
                    ((MGameOutputBoundary) presenter).PrepareError();
                }
                if (!error) {
                    MGameOutputData gameOutputData = new MGameOutputData(isAcceptable, gameStates.getScore(),
                            null, 0, null, gameStates.getRounds() + 1, oppScore);

                    presenter.endGame(gameOutputData);
                }
            }
        }).start();
    }
}
