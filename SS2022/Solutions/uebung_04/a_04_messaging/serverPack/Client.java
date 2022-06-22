package Solutions.uebung_04.a_04_messaging.serverPack;

import Development.uebung03.Server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Client {
    // public static int SERVER_PORT = 1213;
    public static String SERVER_HOST = "localhost";

    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);

        try {
            Socket zumServer = new Socket(SERVER_HOST, MessagingService.SERVER_PORT);

            InputStream in = zumServer.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            OutputStream out = zumServer.getOutputStream();
            PrintWriter writer = new PrintWriter(out);


            String ServerCmd = "";
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty() || line.contains("!")) {
                    break;
                }
                ServerCmd += line;
                System.out.println(line);
            }
            System.out.println(ServerCmd);

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
