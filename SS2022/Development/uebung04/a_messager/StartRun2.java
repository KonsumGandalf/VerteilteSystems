package Development.uebung04.a_messager;


import java.io.IOException;
import java.net.Socket;

public class StartRun2 {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", Client.SERVER_PORT);

            new Thread(new Client(socket)).start();

        } catch(IOException ex) {
            ex.printStackTrace();
        }

    }
}