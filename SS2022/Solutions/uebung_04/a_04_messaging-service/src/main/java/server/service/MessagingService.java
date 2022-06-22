package server.service;

import server.repository.MessageStore;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessagingService {

    public static int SERVER_PORT = 1213;

    public static void main(String[] args) {

        ExecutorService threadpool = Executors.newFixedThreadPool(10);
        MessageStore database = new MessageStore();

        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

            while(true) {
                Socket clientSocket = serverSocket.accept();

                Runnable request = new ClientRequest(clientSocket, database);
                threadpool.execute(request);

            }

        } catch (IOException ex) {

        }
    }
}
