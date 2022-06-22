package Development.uebung03.c_simple_sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final String END_STRING = "ENDE";
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(3333)){
            while(true){
                Socket client = serverSocket.accept();

                new Thread(new InputStreamListener(client)).start();
                new Thread(new KeyboardListener(client)).start();
            }
        } catch (IOException e){
            System.out.println("Error reading the server");
        }
    }
}
