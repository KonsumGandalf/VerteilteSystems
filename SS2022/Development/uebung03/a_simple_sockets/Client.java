package Development.uebung03.a_simple_sockets;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static int PORT_SERVER = 1871;
    public static String HOST_SERVER = "localhost";

    public static void  main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        try {
            Socket serverConnection = new Socket(HOST_SERVER, PORT_SERVER);

            InputStream in = serverConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            OutputStream out = serverConnection.getOutputStream();
            PrintWriter writer = new PrintWriter(out);

            String serverCommand = reader.readLine();
            System.out.println(serverCommand);
            String clientResponse = keyboard.nextLine();

            writer.println(clientResponse);
            writer.flush();

            String serverResponse = reader.readLine();
            System.out.println("Response of the server: "+serverResponse);

        } catch (IOException e) {
            System.out.println("Issues on the Clientside");
        }
    }
}
