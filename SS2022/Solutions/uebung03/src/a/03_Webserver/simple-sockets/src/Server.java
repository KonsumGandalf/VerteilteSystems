import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static int PORT = 1234;

    public static void main(String[] args) {

        // Falls Sie den Port als Argument von der Konsole einlesen wollen (wie in Aufgabenstellung)
        // PORT = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while(true) {

                System.out.println("Warte auf Verbindungen...");

                Socket zuNeuemClient = serverSocket.accept();
                System.out.println("Neue Verbindung von: " + zuNeuemClient.toString());

                InputStream in  = zuNeuemClient.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                OutputStream out = zuNeuemClient.getOutputStream();
                PrintWriter writer = new PrintWriter(out);

                // in diesem Beispiel empfängt Server zuerst und sendet zurück:

                String anfrageVomClient = reader.readLine(); // blockiert, bis Client String gesendet + geflushed hat
                System.out.println(anfrageVomClient);

                writer.println("*" + anfrageVomClient + "*");
                writer.flush();

                zuNeuemClient.close(); // Verbindung und Streams hier und auch auf Client-Seite automatisch geschlossen
            }


        } catch (IOException e) {
            System.out.println("FEHLER MIT SERVER-SOCKET:");
            e.printStackTrace();
        }

    }


}
