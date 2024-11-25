package use_case.multiplayer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.Server;
import use_case.game.GameInputData;
import use_case.mgame.MGameInputBoundary;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class MultiplayerInteractorTest {

    @BeforeAll
    public static void start(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    new Server(1122);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }


    @Test
    void execute() {
        Presenter presenter1 = new Presenter();

        Presenter presenter2 = new Presenter();

        InputB inputBoundary1 = new InputB();

        InputB inputBoundary2 = new InputB();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                MultiplayerInteractor interactor1 = new MultiplayerInteractor("localhost", 1122, presenter1, inputBoundary1);
                interactor1.execute(new MultiplayerInputData("Alice", "Bob"));
            }
        });

        t2.start();
        MultiplayerInteractor interactor2 = new MultiplayerInteractor("localhost", 1122, presenter2, inputBoundary2);

        interactor2.execute(new MultiplayerInputData("Bob", "Alice"));

        assertEquals("123", inputBoundary2.out);
    }

    @Test
    void executeTimeout() {

        Presenter presenter1 = new Presenter();

        InputB inputBoundary1 = new InputB();

        MultiplayerInteractor interactor2 = new MultiplayerInteractor("localhost", 1122, presenter1, inputBoundary1);
        interactor2.execute(new MultiplayerInputData("H", "G"));
        assertEquals(presenter1.out, "timeout");

    }

    @Test
    void executeError() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new MockServer();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        Presenter presenter1 = new Presenter();
        MGameInputBoundary inputBoundary1 = new InputB();
        MultiplayerInteractor interactor2 = new MultiplayerInteractor("localhost", 1222, presenter1, inputBoundary1);
        interactor2.execute(new MultiplayerInputData("Bob", "Alice"));
        assertEquals(presenter1.out, "error");

    }

    private static class MockServer {
        public MockServer() throws IOException {
            ServerSocket serverSocket = new ServerSocket(1222);
            Socket socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            in.close();
            socket.close();
        }
    }

    private static class InputB implements MGameInputBoundary {


        public String out;

        @Override
        public void startMGame(long seed, Socket socket) {
            System.out.println("game started");
            try {
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                assertInstanceOf(Long.class, seed);
                out.writeUTF("123");
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());
                out = in.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void handleGuess(GameInputData input) {

        }

        @Override
        public void init() {

        }
    }


    private static class Presenter implements MultiplayerOutputBoundary {

        public String out;

        @Override
        public void prepareTimeoutView() {
            out = "timeout";
        }

        @Override
        public void prepareErrorView() {
            out = "error";
        }
    }
}