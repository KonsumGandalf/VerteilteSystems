import java.io.*;
import java.net.Socket;
import java.util.Scanner;



public class Client {

    public static String SERVER_HOSTNAME = "localhost";
    public static int    SERVER_PORT     = 1234;

    public static void main(String[] args) {

        // Falls Sie den Hostname und Port als Argumente von der Konsole einlesen wollen (wie in Aufgabenstellung)
        // SERVER_HOSTNAME = args[0];
        // SERVER_PORT = Integer.parseInt(args[1]);

        Scanner keyboard = new Scanner(System.in);

        try {
            Socket zumServer = new Socket(SERVER_HOSTNAME, SERVER_PORT);

            InputStream in  = zumServer.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            OutputStream out = zumServer.getOutputStream();
            PrintWriter writer = new PrintWriter(out);

            // Text von Tastatur einlesen:
            System.out.println("Bitte geben Sie einen Text ein:");
            String textFuerServer = keyboard.nextLine();

            // in diesem Beispiel empfängt Server zuerst und sendet zurück:

            writer.println(textFuerServer);
            writer.flush();


            String antwortVomServer = reader.readLine(); // blockiert, bis String String gesendet + geflushed hat
            System.out.println("Antwort von Server:" + antwortVomServer);

            // Socket wird von Gegenseite geschlossen

        } catch (IOException e) {
            System.out.println("FEHLER MIT SOCKET:");
            e.printStackTrace();
        }


    }

}
