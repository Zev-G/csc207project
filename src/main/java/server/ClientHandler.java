package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHandler implements Runnable {

    private final String username;
    private final String opponentUsername;
    private final Socket userSocket;
    private Socket opponentSocket;
    private final Server server;
    private boolean gameStarted;

    private final ExecutorService executorService;

    private int score1 = -1;

    private int score2 = -1;

    public ClientHandler(Server server, String username, String opponentUsername, Socket userSocket) {
        this.server = server;
        this.username = username;
        this.opponentUsername = opponentUsername;
        this.userSocket = userSocket;
        this.executorService = Executors.newFixedThreadPool(2);  // Use a pool for managing threads
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
            sendSeed();

            executorService.submit(() -> handlePlayerInput(userSocket, 1));
            executorService.submit(() -> handlePlayerInput(opponentSocket, 2));

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void sendSeed() throws IOException {
        final DataOutputStream dout1 = new DataOutputStream(userSocket.getOutputStream());
        final DataOutputStream dout2 = new DataOutputStream(opponentSocket.getOutputStream());
        final Random rand = new Random();
        final long seed = rand.nextLong();
        // Send the seed away
        dout1.writeUTF(String.valueOf(seed));
        dout2.writeUTF(String.valueOf(seed));
        dout1.flush();
        dout2.flush();
    }

    @Override
    public void run() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (!gameStarted) {
                    try {
                        final DataOutputStream out = new DataOutputStream(userSocket.getOutputStream());
                        out.writeUTF("timeout");
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

    private void handlePlayerInput(Socket socket, int playerId) {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            int score = Integer.parseInt(in.readUTF());
            if (playerId == 1) {
                score1 = score;
            } else {
                score2 = score;
            }
            if (score1 >= 0 && score2 >= 0) {
                sendAway();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendAway(){
        System.out.println("sent");
        try {
            final DataOutputStream dout1 = new DataOutputStream(userSocket.getOutputStream());
            final DataOutputStream dout2 = new DataOutputStream(opponentSocket.getOutputStream());
            dout1.writeUTF(String.valueOf(score2));
            dout2.writeUTF(String.valueOf(score1));
            dout1.flush();
            dout2.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
