package use_case.multiplayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
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
            public void prepareGame(MultiplayerOutputData multiplayerOutputData) {
                System.out.println(multiplayerOutputData.getSeed() + " " + multiplayerOutputData.getSocket());

                Scanner s = new Scanner(System.in);

                String str = s.nextLine();

                try {
                    DataOutputStream out = new DataOutputStream(multiplayerOutputData.getSocket().getOutputStream());
                    out.writeUTF(str);
                    out.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                try {
                    DataInputStream in = new DataInputStream(multiplayerOutputData.getSocket().getInputStream());
                    System.out.println(in.readUTF());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        MultiplayerInteractor interactor = new MultiplayerInteractor("localhost", 5555, presenter);
        Scanner s = new Scanner(System.in);
        String name = s.nextLine().trim();

        String name2 = s.nextLine().trim();

        interactor.execute(new MultiplayerInputData(name, name2));


    }
}
