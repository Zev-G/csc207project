package use_case.multiplayer;

import org.junit.jupiter.api.Test;
import server.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class MultiplayerInteractorTest {


    @Test
    void execute() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new Server(1234);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


        Presenter presenter1 = new Presenter();

        Presenter presenter2 = new Presenter();

        new Thread(new Runnable() {
            @Override
            public void run() {
                MultiplayerInteractor interactor1 = new MultiplayerInteractor("localhost", 1234, presenter1);
                interactor1.execute(new MultiplayerInputData("Alice", "Bob"));
            }
        }).start();
        MultiplayerInteractor interactor2 = new MultiplayerInteractor("localhost", 1234, presenter2);
        interactor2.execute(new MultiplayerInputData("Bob", "Alice"));

        assertEquals(presenter2.out,"123");
    }

    @Test
    void executeTimeout() {
        Presenter presenter1 = new Presenter();

        MultiplayerInteractor interactor2 = new MultiplayerInteractor("localhost", 1234, presenter1);
        interactor2.execute(new MultiplayerInputData("Bob", "Alice"));
        assertEquals(presenter1.out,"timeout");

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
        MultiplayerInteractor interactor2 = new MultiplayerInteractor("localhost", 1235, presenter1);
        interactor2.execute(new MultiplayerInputData("Bob", "Alice"));
        assertEquals(presenter1.out,"error");

    }

    private static class MockServer {
        public MockServer() throws IOException {
            ServerSocket serverSocket = new ServerSocket(1235);
            Socket socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            in.close();
            socket.close();
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

        @Override
        public void prepareGame(MultiplayerOutputData multiplayerOutputData) {
            try {
                DataOutputStream out = new DataOutputStream(multiplayerOutputData.getSocket().getOutputStream());
                assertInstanceOf(Long.class, multiplayerOutputData.getSeed());
                out.writeUTF("123");
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                DataInputStream in = new DataInputStream(multiplayerOutputData.getSocket().getInputStream());
                out = in.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}