package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Multiplayer Server
 * This class is the server side for multiplayer support.
 */
public class Server {
    private final ServerSocket serverSocket;

    private final Map<String, ClientHandler> username2ClientHandler = new HashMap<>();

    /**
     * This is the server to support multiplayer.
     *
     * @param port the port for the program to run
     * @throws IOException May throw IOException.
     */
    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Service started, press q to exit.");
        start();
    }

    private void start() {
        new Thread(new Console()).start();
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                final DataInputStream in = new DataInputStream(socket.getInputStream());
                final String[] names = in.readUTF().split(",");
                if (names.length != 2) {
                    System.out.println("connection error");
                    continue;
                }
                final String name = names[0];
                final String name2 = names[1];
                if (username2ClientHandler.containsKey(name2)
                        && username2ClientHandler.get(name2).getOpponentUsername().equals(name)) {
                    System.out.println("exist");
                    username2ClientHandler.get(name2).connect(socket);
                } else {
                    final ClientHandler clientHandler = new ClientHandler(this, name, name2, socket);
                    final Thread clientThread = new Thread(clientHandler);
                    username2ClientHandler.put(name, clientHandler);
                    System.out.println(username2ClientHandler);
                    clientThread.start();
                }
            } catch (IOException e) {
                System.out.println("connection error");
            }
        }
    }

    private static final class Console implements Runnable {
        @Override
        public void run() {
            while (true) {
                final Scanner scanner = new Scanner(System.in);
                if (scanner.next().equals("q")) {
                    System.exit(0);
                }
            }
        }
    }

    /**
     * To remove a user from connecting.
     *
     * @param username the username of the user
     */
    public void remove(String username) {
        username2ClientHandler.remove(username);
    }

    /**
     * To run a server service.
     *
     * @param args input argument
     * @throws RuntimeException runtime exception
     */
    public static void main(String[] args) {
        try {
            new Server(Constants.PORT);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
