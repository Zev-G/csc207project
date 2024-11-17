package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ClientHandler implements Runnable {

    private final String username;
    private final String opponentUsername;
    private final Socket userSocket;
    private Socket opponentSocket;
    private final Server server;
    private boolean gameStarted;

    public ClientHandler(Server server, String username, String opponentUsername, Socket userSocket) {
        this.server = server;
        this.username = username;
        this.opponentUsername = opponentUsername;
        this.userSocket = userSocket;
    }

    public String getOpponentUsername(){
        return opponentUsername;
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
//            DataInputStream in1 = new DataInputStream(userSocket.getInputStream());
//            System.out.println(in1.readUTF());
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
