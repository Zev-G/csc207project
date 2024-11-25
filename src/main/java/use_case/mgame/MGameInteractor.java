package use_case.mgame;

import data_access.LocationDataAccess;
import entity.PhotoLocation;
import use_case.game.GameInteractor;
import use_case.game.GameOutputData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MGameInteractor extends GameInteractor implements MGameInputBoundary {

    private Socket socket;

    public MGameInteractor(LocationDataAccess photoAccess, MGameOutputBoundary presenter) {
        super(photoAccess, presenter);
    }

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

    @Override
    protected void endGame(boolean isAcceptable) {

        ((MGameOutputBoundary) presenter).waitForRespond();

        int oppScore = 0;

        boolean error = false;

        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(String.valueOf(gameStates.getScore()));
            out.flush();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            oppScore = Integer.parseInt(in.readUTF());

        } catch (IOException e) {
            error = true;
            ((MGameOutputBoundary) presenter).PrepareError();
        }

        if (!error) {
            MGameOutputData gameOutputData = new MGameOutputData(isAcceptable, gameStates.getScore(),
                    null, 0, null, gameStates.getRounds() + 1, oppScore);

            presenter.endGame(gameOutputData);
        }
    }
}