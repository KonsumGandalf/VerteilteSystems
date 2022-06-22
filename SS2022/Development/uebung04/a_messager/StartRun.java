package Development.uebung04.a_messager;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartRun {

    public static void main(String[] args) {

        ExecutorService threadpool = Executors.newFixedThreadPool(10);

        try {
            ServerSocket serverSocket = new ServerSocket(Client.SERVER_PORT);

            while(true) {
                Socket clientSocket = serverSocket.accept();

                Runnable request = new Client(clientSocket);
                threadpool.execute(request);

            }

        } catch (IOException ex) {

        }
    }
}


