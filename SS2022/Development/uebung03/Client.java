package Development.uebung03;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static int SERVER_PORT = 4444;
    public static String SERVER_HOST = "localhost";

    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);

        try {
            Socket zumServer = new Socket(SERVER_HOST, SERVER_PORT);

            InputStream in = zumServer.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            OutputStream out = zumServer.getOutputStream();
            PrintWriter writer = new PrintWriter(out);

            String serverAnweisung = reader.readLine();
            System.out.println(serverAnweisung);
            String textFuerServer = keyboard.nextLine();

            writer.println(textFuerServer);
            writer.flush();

            String serverAntwort = reader.readLine();
            System.out.println("Antwort von Server:" + serverAntwort);
        } catch (IOException e){
            System.out.println("Fehler im Socket Client");
            e.printStackTrace();
        }
    }
}
