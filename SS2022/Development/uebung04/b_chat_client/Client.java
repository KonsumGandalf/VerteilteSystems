package Development.uebung04.b_chat_client;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Random;
import java.util.Scanner;

public class Client {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8888;

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
        new Thread(new ClientReceiver(socket)).start();
        new Thread(new ClientSender(socket)).start();
    }
}

class ClientSender implements Runnable{
    private Socket socket;
    private boolean running = true;
    private String username;

    ClientSender(Socket socket){
        this.socket = socket;
        this.username = "MeinUsername"+(new Random().nextInt(999));
    }

    @Override
    public void run(){
        try{
            Scanner keyboard = new Scanner(System.in);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            System.out.println(">>> Do you want to set your Username? Default: "+this.username);
            System.out.println(">>> [Yes]=input of username or [No]");
            String newUsername;
            if(!((newUsername = keyboard.nextLine()).equalsIgnoreCase("no"))){
                this.username = newUsername;
            }

            writer.println("OPEN#"+this.username);
            writer.flush();
            System.out.println(" >>> User '" + username + "' wurde am Server angemeldet.");
            System.out.println(" >>> Tippen Sie   EXIT   zum Beenden des Clients.");

            while(running){
                String input = keyboard.nextLine();
                if (input.equalsIgnoreCase("EXIT")) {
                    writer.println("EXIT");
                    writer.flush();
                    socket.close();
                    System.out.println(" >>> User wurde abgemeldet und Socket geschlossen. Auf Wiedersehen.");
                    running = false;
                } else {
                    writer.println("PUBL#"+input);
                    writer.flush();
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(" >>> Sender beendet");
    }
}

class ClientReceiver implements Runnable{
    private Socket socket;
    private boolean running = true;
    ClientReceiver(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (running) {
                String input = reader.readLine();
                if (input != null) {
                    if (input.startsWith("EXIT")) {
                        running = false;
                        System.out.println(" >>> Server hat die Chat-Verbindung beendet.");
                        socket.close();
                    } else if (input.startsWith("SHOW")) {
                        String[] nachricht = input.split("#");
                        System.out.println(" >>> " + nachricht[1] + ": " + nachricht[2]);
                    } else if (input.startsWith("ADMIN")) {
                        String[] nachricht = input.split("#");
                        System.out.println(" >>> " + nachricht[1]);
                    } else {
                        System.out.println(" >>> Unknown prefix: " + input);
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println(" >>> Listener beendet");
    }
}
