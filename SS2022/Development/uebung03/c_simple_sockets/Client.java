package Development.uebung03.c_simple_sockets;

import java.io.IOException;
import java.net.Socket;
import java.security.Key;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3333);

            new Thread(new KeyboardListener(socket)).start();
            new Thread(new InputStreamListener(socket)).start();
        } catch (IOException e){
            System.out.println("Error with creating Client");
            e.printStackTrace();
        }
    }
}
