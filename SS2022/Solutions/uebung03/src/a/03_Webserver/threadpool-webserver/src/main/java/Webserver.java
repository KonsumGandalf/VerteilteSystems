
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Webserver {
    
    public static final int DEFAULT_PORT = 8080;
    public static final int THREAD_POOL_SIZE = 10;
    public final int myPort;
    public final ExecutorService threadPool;

    public static void main(String[] args) {
        new Webserver(DEFAULT_PORT);
    }
    
    public Webserver(int port) {
        this.myPort = port;
        this.threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        startWebserver();
    }
    
    private void startWebserver() {
        try {
            ServerSocket serverSocket = new ServerSocket(this.myPort);
            System.out.println("ServerSocket gestartet...");
            while(true) {
                // Auf neue Verbindung eines Browsers warten...
                Socket clientRequest = serverSocket.accept();
                System.out.println("Neuer Request von " + clientRequest.getInetAddress().getHostAddress());
                
                // ...sobald neue Verbindung ankommt, Runnable erzeugen und an ThreadPool übergeben
                Runnable requestHandler = new RequestHandler(clientRequest);
                this.threadPool.execute(requestHandler);
                
            }
        } catch (IOException ex) {
            // 
        }
    }
    
}
