package use_case.multiplayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MultiplayerInteractor implements MultiplayerInputBoundary {

    private String host;
    private int port;

    private MultiplayerOutputBoundary presenter;

    public MultiplayerInteractor(String host, int port, MultiplayerOutputBoundary presenter) {
        this.host = host;
        this.port = port;
        this.presenter = presenter;
    }

    @Override
    public void execute(MultiplayerInputData multiplayerInputData) {
        try {
            final Socket socket = new Socket(host, port);
            final DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(String.format("%s,%s", multiplayerInputData.getUsername(),
                    multiplayerInputData.getOpponentUsername()));
            out.flush();

            final DataInputStream dis = new DataInputStream(socket.getInputStream());

            final String str = (String) dis.readUTF();

            if ("timeout".equals(str)) {
                presenter.prepareTimeoutView();
            } else {
                final long seed = Long.parseLong(str);
                presenter.prepareGame(new MultiplayerOutputData(seed, socket));
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
