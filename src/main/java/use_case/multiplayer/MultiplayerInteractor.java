package use_case.multiplayer;

import use_case.mgame.MGameInputBoundary;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


/**
 * This class connects the user to the server and returns the needed info.
 */
public class MultiplayerInteractor implements MultiplayerInputBoundary {

    private String host;
    private int port;

    private boolean tryToConnet;

    private MultiplayerOutputBoundary presenter;

    private MGameInputBoundary mGameInteractor;


    /**
     * This creates a Multiplayer interactor.
     *
     * @param host      the host name
     * @param port      the port
     * @param presenter a presenter
     */
    public MultiplayerInteractor(String host, int port, MultiplayerOutputBoundary presenter, MGameInputBoundary mGameInteractor) {
        this.host = host;
        this.port = port;
        this.presenter = presenter;
        this.mGameInteractor = mGameInteractor;
    }

    /**
     * To connect to the server
     *
     * @param multiplayerInputData the input data
     * @throws IOException may throw IO exception
     */
    @Override
    public void execute(MultiplayerInputData multiplayerInputData) {

        try {
            final Socket socket = new Socket(host, port);
            final DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(String.format("%s,%s", multiplayerInputData.getUsername(),
                    multiplayerInputData.getOpponentUsername()));
            out.flush();

            final DataInputStream inputStream = new DataInputStream(socket.getInputStream());

            if (!tryToConnet) {
                System.out.println("try to connect");
                tryToConnet = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String str = "error";
                        try {
                            str = (String) inputStream.readUTF();
                        } catch (IOException e) {
                            presenter.prepareErrorView();
                        }

                        if ("timeout".equals(str)) {
                            presenter.prepareTimeoutView();
                        } else if ("error".equals(str)) {
                            presenter.prepareErrorView();
                        } else {
                            final long seed = Long.parseLong(str);
                            tryToConnet = false;
                            mGameInteractor.startMGame(seed, socket);
                        }
                    }
                }).start();
            }else {
                System.out.println("please wait");
            }
        } catch (IOException exception) {
            presenter.prepareErrorView();
        }

    }
}
