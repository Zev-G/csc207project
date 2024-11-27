package use_case.mgame;

import data_access.DataAccessMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import server.Server;
import use_case.game.GameInputData;
import use_case.game.GameOutputData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class MGameInteractorTest {

    private static class MGamePresenterMock implements MGameOutputBoundary {

        String state;

        GameOutputData data;

        @Override
        public void handleGuess(GameOutputData gameOutputData) {
            data = gameOutputData;
        }

        @Override
        public void init(GameOutputData gameOutputData) {
            data = gameOutputData;
        }

        @Override
        public void endGame(GameOutputData gameOutputData) {
            data = gameOutputData;
            state = "end";
        }

        @Override
        public void waitForResponse() {
            state = "wait";
        }

        @Override
        public void PrepareError() {
            state = "error";
        }
    }

    @BeforeAll
    public static void start() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    new Server(8888);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @Test
    void startMGame() throws IOException {

        MGamePresenterMock presenterMock = new MGamePresenterMock();


        MGameInteractor interactor = new MGameInteractor(new DataAccessMock(), presenterMock);
        Socket socket = new Socket("localhost", 8888);
        interactor.startMGame(1000, socket);

        assertEquals(1, presenterMock.data.getRound());

    }

    private static class MockServer1 {
        public MockServer1() throws IOException {
            ServerSocket serverSocket = new ServerSocket(7777);
            Socket socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("123");
            out.flush();

        }
    }

    @Test
    void endGame() throws IOException, InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new MockServer1();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        MGamePresenterMock presenterMock = new MGamePresenterMock();


        MGameInteractor interactor = new MGameInteractor(new DataAccessMock(), presenterMock);
        Socket socket = new Socket("localhost", 7777);
        interactor.startMGame(1000, socket);
        for (int i = 0; i < 10; i++) {
            interactor.handleGuess(new GameInputData(0, new double[]{0, 0}, new double[]{1, 1}));
        }

        Thread.sleep(500);

        assertEquals(11, presenterMock.data.getRound());

        assertEquals(123, ((MGameOutputData) presenterMock.data).getOpponentScore());

    }

    private static class MockServer2 {
        public MockServer2() throws IOException {
            ServerSocket serverSocket = new ServerSocket(1223);
            Socket socket = serverSocket.accept();
            socket.close();
        }
    }

    @Test
    void testError() throws IOException, InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new MockServer2();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        MGamePresenterMock presenterMock = new MGamePresenterMock();


        MGameInteractor interactor = new MGameInteractor(new DataAccessMock(), presenterMock);
        Socket socket = new Socket("localhost", 1223);
        interactor.startMGame(1000, socket);
        for (int i = 0; i < 10; i++) {
            interactor.handleGuess(new GameInputData(0, new double[]{0, 0}, new double[]{1, 1}));
        }

        Thread.sleep(500);

        assertEquals("error", presenterMock.state);

    }

}