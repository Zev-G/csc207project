package use_case.multiplayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MultiplayerInteractorTest {

    @Test
    void execute() {
        Presenter presenter1 = new Presenter();

        Presenter presenter2 = new Presenter();

        new Thread(new Runnable() {
            @Override
            public void run() {
                MultiplayerInteractor interactor1 = new MultiplayerInteractor("173.32.226.108", 5555, presenter1);
                interactor1.execute(new MultiplayerInputData("Alice", "Bob"));
            }
        }).start();

        MultiplayerInteractor interactor2 = new MultiplayerInteractor("173.32.226.108", 5555, presenter2);
        interactor2.execute(new MultiplayerInputData("Bob", "Alice"));

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