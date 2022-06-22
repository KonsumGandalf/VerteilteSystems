package Development.uebung04.b_chat_client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server implements Runnable{
    private int SERVER_PORT = 8888;
    private Dispatcher dispatcher;
    Server(Dispatcher dispatcher){
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)){
            while(true){
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientEndpoint(clientSocket, this.dispatcher)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Dispatcher dispatcher = new Dispatcher();

        new Thread(new Server(dispatcher)).start();

    }
}

class Dispatcher {
    private Set<ClientEndpoint> connectedClients = new TreeSet<>();
    private Lock monitor = new ReentrantLock();

    public void addClient(ClientEndpoint client) {
        this.monitor.lock();
        adminDispatchMessage(client.username + " has entered the chat. Welcome!");
        connectedClients.add(client);
        this.monitor.unlock();
    }

    public void removeClient(ClientEndpoint client) {
        this.monitor.lock();
        connectedClients.remove(client);
        adminDispatchMessage(client.username + " has left the chat. Bye!");
        this.monitor.unlock();
    }

    public void dispatchMessage(String msg, ClientEndpoint sender, String receiverUsername) {
        this.monitor.lock();
        for(ClientEndpoint receiver: this.connectedClients){
            if (receiverUsername.equalsIgnoreCase(receiver.username) || receiver.username.equalsIgnoreCase("all")){
                receiver.writer.println("SHOW#" + sender.username + "#" + msg);
                receiver.writer.flush();
            }
        }
        this.monitor.unlock();
    }

    public void adminDispatchMessage(String msg) {
        this.monitor.lock();
        for(ClientEndpoint receiver: this.connectedClients){
            // if (!receiver.equals())
            receiver.writer.println("ADMIN#" + msg);
            receiver.writer.flush();
        }
        this.monitor.unlock();
    }

}

class ClientEndpoint implements Runnable, Comparable<ClientEndpoint> {
    private Socket socket;
    private Dispatcher dispatcher;
    private boolean running = true;
    protected PrintWriter writer;
    protected BufferedReader reader;
    protected String username;
    protected String cmd;
    ClientEndpoint(Socket socket, Dispatcher dispatcher){
        this.socket = socket;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        try {
            this.writer = new PrintWriter(socket.getOutputStream());
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String openMsg = reader.readLine();
            if ( openMsg == null || !openMsg.startsWith("OPEN#")){
                this.writer.print("ADMN#Error, client has to send OPEN followed by HASH and USERNAME as first message. Socket will be closed by server.");
                this.writer.flush();
                this.socket.close();
                return;
            }

            String[] msgParts = openMsg.split("#");
            this.username = msgParts[1].trim();
            this.dispatcher.addClient(this);
            this.writer.println("ADMIN#Welcome "+this.username);
            this.writer.flush();
            System.out.println("LOG:New Client for " + this.username + " added");

            while(this.running){
                String msg = this.reader.readLine();

                if (msg.startsWith("EXIT")) {
                    this.dispatcher.removeClient(this);
                    this.running = false;
                } else if (msg.startsWith("PUBL#")) {
                    this.dispatcher.dispatchMessage(msg.split("#")[2], this, msg.split("#")[1]);
                } else {
                    this.writer.println("ADMN#Error, unkown command: " + msg);
                    this.writer.flush();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                this.socket.close();
                e.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            this.dispatcher.removeClient(this);
        }
    }

    @Override
    public boolean equals(Object other) {
        if( !(other instanceof ClientEndpoint) )
            return false;

        ClientEndpoint otherEndpoint = (ClientEndpoint)other;

        return this.username.equals(otherEndpoint.username);
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

    @Override
    public int compareTo(ClientEndpoint other) {
        return this.username.compareTo(other.username);
    }


}