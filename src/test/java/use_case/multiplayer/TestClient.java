package use_case.multiplayer;

import use_case.game.GameInputData;
import use_case.mgame.MGameInputBoundary;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * A Testing Client Code.
 */
public class TestClient {
    public static void main(String[] args) {
        MultiplayerOutputBoundary presenter = new MultiplayerOutputBoundary() {
            @Override
            public void prepareTimeoutView() {
                System.out.println("timeout");
            }

            @Override
            public void prepareErrorView() {
                System.out.println("error");
            }
        };

        MGameInputBoundary inputBoundary = new MGameInputBoundary() {
            @Override
            public void startMGame(long seed, Socket socket) {
                System.out.println("game started");
            }

            @Override
            public void handleGuess(GameInputData input) {

            }

            @Override
            public void init() {

            }

            @Override
            public void timeout(GameInputData input) {

            }
        };

        MultiplayerInteractor interactor = new MultiplayerInteractor("app.kristopherz.net", 5555, presenter, inputBoundary);
        Scanner s = new Scanner(System.in);
        String name = s.nextLine().trim();

        String name2 = s.nextLine().trim();


        interactor.execute(new MultiplayerInputData(name, name2));


    }
}
