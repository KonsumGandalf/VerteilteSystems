package Development.uebung03.c_simple_sockets_2;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", Server.PORT);

            new Thread(new util.InputStreamListener(socket)).start();
            new Thread(new util.KeyboardListener(socket)).start();

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
