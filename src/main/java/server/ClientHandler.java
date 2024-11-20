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

/**
 * The class handles user connections.
 */
public class ClientHandler implements Runnable {

    private final String username;
    private final String opponentUsername;
    private final Socket userSocket;
    private Socket opponentSocket;
    private final Server server;

    private boolean gameStarted;

    private boolean gameEnded;

    private final ExecutorService executorService;

    private int score1 = -1;

    private int score2 = -1;

    /**
     * This creates a client handler.
     *
     * @param server           the server that keeps all the threads.
     * @param username         the username
     * @param opponentUsername the opponent username
     * @param userSocket       user's socket
     */
    public ClientHandler(Server server, String username, String opponentUsername, Socket userSocket) {
        this.server = server;
        this.username = username;
        this.opponentUsername = opponentUsername;
        this.userSocket = userSocket;
        // Use a pool for managing threads
        this.executorService = Executors.newFixedThreadPool(2);
    }

    /**
     * To get the opponent username.
     *
     * @return opponent username
     */
    public String getOpponentUsername() {
        return opponentUsername;
    }

    /**
     * To connect.
     *
     * @param opponentSocket opponent socket
     */
    public void connect(Socket opponentSocket) {
        System.out.println("connected");
        this.opponentSocket = opponentSocket;
        startGame();
    }

    private void startGame() {
        gameStarted = true;

        // Give both users a random seed to generate the same sequence of photos
        sendSeed();

        executorService.submit(() -> handlePlayerInput(userSocket, 1));
        executorService.submit(() -> handlePlayerInput(opponentSocket, 2));


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (!gameEnded) {
                    System.out.println("user timeout");
                    server.remove(username);
                    try {
                        final DataOutputStream out1 = new DataOutputStream(userSocket.getOutputStream());
                        out1.writeUTF("timeout");
                        out1.flush();
                        out1.close();
                        userSocket.close();
                        final DataOutputStream out2 = new DataOutputStream(opponentSocket.getOutputStream());
                        out2.writeUTF("timeout");
                        out2.flush();
                        out2.close();
                        opponentSocket.close();
                    } catch (IOException exception) {
                        userDisconnected();
                    }
                }

            }
        }, Constants.GAME_TIME * Constants.SECOND2MILLISECOND);
    }

    private void sendSeed() {
        try {
            final DataOutputStream dout1 = new DataOutputStream(userSocket.getOutputStream());
            final DataOutputStream dout2 = new DataOutputStream(opponentSocket.getOutputStream());
            final Random rand = new Random();
            final long seed = rand.nextLong();
            // Send the seed away
            dout1.writeUTF(String.valueOf(seed));
            dout2.writeUTF(String.valueOf(seed));
            dout1.flush();
            dout2.flush();
        } catch (IOException e) {
            userDisconnected();
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
                        out.writeUTF("timeout");
                        server.remove(username);
                        out.flush();
                        out.close();
                        userSocket.close();
                    } catch (IOException exception) {
                        userDisconnected();
                    }
                }

            }
        }, Constants.TIME_OUT_TIME * Constants.SECOND2MILLISECOND);
    }

    private void handlePlayerInput(Socket socket, int playerId) {
        try {
            final DataInputStream in = new DataInputStream(socket.getInputStream());
            final int score = Integer.parseInt(in.readUTF());
            if (playerId == 1) {
                score1 = score;
            } else {
                score2 = score;
            }
            if (score1 >= 0 && score2 >= 0) {
                sendAway();
            }
        } catch (IOException exception) {
            userDisconnected();
        }
    }

    private void sendAway() {
        System.out.println("sent");
        try {
            final DataOutputStream dout1 = new DataOutputStream(userSocket.getOutputStream());
            final DataOutputStream dout2 = new DataOutputStream(opponentSocket.getOutputStream());
            dout1.writeUTF(String.valueOf(score2));
            dout2.writeUTF(String.valueOf(score1));
            dout1.flush();
            dout2.flush();
            userSocket.close();
            opponentSocket.close();
        } catch (IOException exception) {
            userDisconnected();
        }
        gameEnded = true;
    }

    private void userDisconnected() {
        System.out.println("user is disconnected");
        server.remove(username);
        try {
            final DataOutputStream dout1 = new DataOutputStream(userSocket.getOutputStream());
            dout1.writeUTF("userDisconnected");
            dout1.flush();
            userSocket.close();
            if (opponentSocket != null) {
                final DataOutputStream dout2 = new DataOutputStream(opponentSocket.getOutputStream());
                dout2.writeUTF("userDisconnected");
                dout2.flush();
                opponentSocket.close();
            }

        } catch (IOException exception) {
            System.out.println("Some message is sent unsuccessfully.");
        }
    }

}
