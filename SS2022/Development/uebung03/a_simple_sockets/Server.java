package Development.uebung03.a_simple_sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static int SERVER_PORT = 1871;
    public static void main(String[] args){
        try(ServerSocket serverSocket = new ServerSocket(SERVER_PORT)){
            while(true){
                System.out.println("Wait for connection");
                Socket clientConnection = serverSocket.accept();

                System.out.println("Client connection established to "+clientConnection.toString());
                InputStream in = clientConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                OutputStream out = clientConnection.getOutputStream();
                PrintWriter writer = new PrintWriter(out);

                writer.println("Please insert a group of chars");
                writer.flush();

                String clientRequest = reader.readLine();
                System.out.println("System-request: " + clientRequest);
                String reversedResponse = new StringBuilder(clientRequest).reverse().toString();
                writer.println(reversedResponse);
                writer.flush();

                clientConnection.close();
            }
        } catch(IOException e){
            System.out.println("Issues on the Serverside");
            e.printStackTrace();
        }
    }
}
