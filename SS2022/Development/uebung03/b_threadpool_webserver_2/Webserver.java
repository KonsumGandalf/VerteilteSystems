package Development.uebung03.b_threadpool_webserver_2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Webserver {

    private static final int DEFAULT_PORT = 8888;
    private static final int THREAD_POOL_SIZE = 10;
    private int myPort;
    private ExecutorService executorService;

    public static void main(String[] args){
        new Webserver(DEFAULT_PORT);
    }

    public Webserver(int port){
        this.myPort = port;
        this.executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        startWebServer();
    }

    public void startWebServer(){
        try (ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT);){
            System.out.println("serverSocket was started");
            while(true){
                try{
                    Socket clientRequest = serverSocket.accept();
                    System.out.println("Neuer Request von " + clientRequest.getInetAddress().getHostAddress());

                    Runnable requestHandler = new RequestHandler(clientRequest);
                    this.executorService.execute(requestHandler);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
