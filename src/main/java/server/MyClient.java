package server;
// Java Program to implement ServerSocket class
// Client - side

// Importing required libraries
import java.io.*;
import java.net.*;
import java.util.Scanner;

// Main class
public class MyClient {

    // Main driver method
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        String name = s.nextLine().trim();

        String name2 = s.nextLine().trim();


        // Try block to check if exception occurs
        try {

            // Creating Socket class object and
            // initializing Socket
            Socket soc = new Socket("localhost", 5555);

            // Invoking input stream via getInputStream()
            // method by creating DataInputStream class
            // object

            DataOutputStream out = new DataOutputStream(soc.getOutputStream());
            out.writeUTF(String.format("%s,%s", name, name2));
            out.flush();

            DataInputStream dis
                    = new DataInputStream(soc.getInputStream());

            String str = (String)dis.readUTF();

            // Display the string on the console
            System.out.println("message= " + str);

            soc.close();
        }

        // Catch block to handle exceptions
        catch (Exception e) {

            // Print the exception on the console
            System.out.println(e);
        }
    }
}
