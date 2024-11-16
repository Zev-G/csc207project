package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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
     * @throws RuntimeException May throw runtime exception.
     */
    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        try {
            start();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void start() throws IOException {
        while (true) {
            final Socket socket = serverSocket.accept();
            final DataInputStream in = new DataInputStream(socket.getInputStream());
            final String[] names = in.readUTF().split(",");
            final String name = names[0];
            final String name2 = names[1];
            if (username2ClientHandler.containsKey(name2)) {
                System.out.println("exist");
                username2ClientHandler.get(name2).connect(socket);
            } else {
                final ClientHandler clientHandler = new ClientHandler(this, name, socket);
                final Thread clientThread = new Thread(clientHandler);
                username2ClientHandler.put(name, clientHandler);
                System.out.println(username2ClientHandler);
                clientThread.start();
            }
        }
    }

    /**
     * To time out a user from connecting.
     *
     * @param username the username of the user
     */
    public void timeOut(String username) {
        username2ClientHandler.remove(username);
    }

    public static void main(String[] args) {
        new Server(5555);
    }
}
