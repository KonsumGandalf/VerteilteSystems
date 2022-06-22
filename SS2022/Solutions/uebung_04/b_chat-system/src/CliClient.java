import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class CliClient {

    public static void main(String[] args) throws Exception {

        // Diese Werte ggf. ueberschreiben:
        String hostname = "localhost";
        int port = 1234;
        String username = "MeinUsername"+(new Random().nextInt(999));

        // Socket zum Server aufbauen
        Socket socket   = new Socket(hostname, port);

        // Zwei Threads starten...
        //    ... der erste zum Empfangen von Nachrichten vom Server und zur Ausgabe ueber System.out.println
        new Thread(new Listener(socket)).start();
        //    ... derzweite zum Senden von Text, der vorher ueber die Tastatur eingegeben wird
        new Thread(new Sender(socket, username)).start();
    }

}


class Sender implements Runnable {
    
    private Socket socket;
    private String username;
    private boolean running = true;
    
    // Konstruktor
    Sender(Socket socket, String username) {
        this.socket  = socket;
        this.username = username;
    }
    
    @Override
    public void run() {
       /*  
        *  Das Senden muss nicht notwendigerweise in ein eigenes Runnable.
        *  Theoretisch koennte dies alles auch in der main-Methode erfolgen.
        */
       try {
          Scanner tastatur = new Scanner(System.in);
          PrintWriter writer = new PrintWriter(socket.getOutputStream());

          writer.println("OPEN#" + username);
          writer.flush();
          System.out.println(" >>> User '" + username + "' wurde am Server angemeldet.");
          System.out.println(" >>> Tippen Sie   EXIT   zum Beenden des Clients.");

          while (running) {
             String input = tastatur.nextLine();
             if (input.equals("EXIT")) {
                writer.println("EXIT");
                writer.flush();
                socket.close(); // sofern der Server das Socket nicht schliesst
                System.out.println(" >>> User wurde abgemeldet und Socket geschlossen. Auf Wiedersehen.");
                running = false;
             } else  {
                writer.println("PUBL#" + input);
                writer.flush();
             } 
          }
       } catch (Exception e) {
          e.printStackTrace();
       }
       System.out.println(" >>> Sender beendet");
    }
}

class Listener implements Runnable {
    
    private Socket socket;
    private boolean running = true;
    
    // Konstruktor
    Listener(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
       try {
          BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          while(running) {
             String input = reader.readLine(); // erzeugt SocketException, falls das Socket geschlossen wird
             if(input != null) {
                if(input.startsWith("EXIT")) {
                   running = false;
                   System.out.println(" >>> Server hat die Chat-Verbindung beendet.");
                   socket.close();
                }
                else if (input.startsWith("SHOW")){
                    String[] nachricht = input.split("#");
                    System.out.println(" >>> " + nachricht[1] + ": " + nachricht[2]);
                }
                else if (input.startsWith("ADMN")){
                    String[] nachricht = input.split("#");
                    System.out.println(" >>> " + nachricht[1]);
                }
                else {
                   System.out.println(" >>> EMPFANGEN: " + input);
                }
             }
             else {
                 // null entspricht "Nachricht komplett, nichts mehr im Pufferspeicher"
             }
          }
       }
       catch(Exception e) {
          //e.printStackTrace();
       }
       System.out.println(" >>> Listener beendet");
    }
}
