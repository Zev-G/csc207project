package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ClientHandler implements Runnable {

    private final String username;
    private final Socket userSocket;
    private Socket opponentSocket;
    private final Server server;
    private boolean gameStarted;

    public ClientHandler(Server server, String username, Socket userSocket) {
        this.server = server;
        this.username = username;
        this.userSocket = userSocket;
    }

    public void connect(Socket opponentSocket) {
        this.opponentSocket = opponentSocket;
        startGame();
    }

    public void startGame() {
        gameStarted = true;
        try {
            // Give both users a random seed to generate the same sequence of photos
            final DataOutputStream dout1 = new DataOutputStream(userSocket.getOutputStream());
            final DataOutputStream dout2 = new DataOutputStream(opponentSocket.getOutputStream());
            final Random rand = new Random();
            final long seed = rand.nextLong();
            // Send the seed away
            dout1.writeUTF(String.valueOf(seed));
            dout2.writeUTF(String.valueOf(seed));
            dout1.flush();
            dout2.flush();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void run() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (!gameStarted) {
                    try {
                        final DataOutputStream out = new DataOutputStream(userSocket.getOutputStream());
                        out.writeUTF("time out");
                        server.timeOut(username);
                        out.flush();
                        out.close();
                        userSocket.close();
                    }
                    catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
            }
        }, Constants.TIME_OUT_TIME * Constants.MINUTE2SECOND * Constants.SECOND2MILLISECOND);
    }
}
