package use_case.multiplayer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MultiplayerInteractorTest {


    @BeforeAll
    static void startServer() {
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
    }

    @Test
    void execute() {
        Presenter presenter1 = new Presenter();

        Presenter presenter2 = new Presenter();

        new Thread(new Runnable() {
            @Override
            public void run() {
                MultiplayerInteractor interactor1 = new MultiplayerInteractor("localhost", 1234, presenter1);
                try {
                    interactor1.execute(new MultiplayerInputData("Alice", "Bob"));
                } catch (IOException e) {
//                    throw new RuntimeException(e);
                }
            }
        }).start();

        MultiplayerInteractor interactor2 = new MultiplayerInteractor("localhost", 1234, presenter2);
        try {
            interactor2.execute(new MultiplayerInputData("Bob", "Alice"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void executeTimeout() {
        Presenter presenter1 = new Presenter();

        MultiplayerInteractor interactor2 = new MultiplayerInteractor("localhost", 1234, presenter1);
        try {
            interactor2.execute(new MultiplayerInputData("Bob", "Alice"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static class Presenter implements MultiplayerOutputBoundary {

        String score;

        @Override
        public void prepareTimeoutView() {
            System.out.println("timeout");
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
                score = in.readUTF();
                assertEquals(score, "123");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}