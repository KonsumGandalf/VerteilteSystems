package Development.uebung03.c_simple_sockets_2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 5454;
    public static final String END_STRING = "Ende";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket client = serverSocket.accept();

                new Thread(new util.InputStreamListener(client)).start();
                new Thread(new util.KeyboardListener(client)).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
