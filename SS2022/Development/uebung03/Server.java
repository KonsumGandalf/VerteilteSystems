package Development.uebung03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static int PORT = 4444;

    public static void main(String args[]){
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while(true){
                System.out.println("Warten auf Verbindung...");

                Socket zuNeuemClient = serverSocket.accept();
                System.out.println("Neue Verbindung von: "+zuNeuemClient.toString());

                InputStream in = zuNeuemClient.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                OutputStream out = zuNeuemClient.getOutputStream();
                PrintWriter writer = new PrintWriter(out);

                writer.println("Bitte gebe einen Text ein:");
                writer.flush();

                String anfrageVonClient = reader.readLine();
                System.out.println(anfrageVonClient);

                writer.println("* "+ anfrageVonClient + " *");
                writer.flush();

                zuNeuemClient.close();
            }


        } catch (IOException e){
            System.out.println("Server-Socket Fehler");
            e.printStackTrace();
        }
    }

}
