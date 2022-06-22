package Solutions.uebung_04.a_04_messaging.serverPack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server extends Thread {

    private int port;
    private Dispatcher dispatcher;

    public Server(int port, Dispatcher dispatcher) {
        this.port = port;
        this.dispatcher = dispatcher;
    }


    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(this.port)) {
            while (true) {
                // blockierend auf neue Socketverbindung warten
                Socket clientSocket = serverSocket.accept();

                ClientEndpoint clientEndpoint = new ClientEndpoint(clientSocket, this.dispatcher);
                new Thread(clientEndpoint).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        // Passives Objekt als "singleton" anlegen...
        Dispatcher dispatcher = new Dispatcher();
        // ... unter weitergeben
        
        int port = 1234;
        Server server = new Server(port, dispatcher);
        server.start();
    }

}

/*
 * Passive Klasse, die von allen Threads (ClientEndpoints und Server) "geteilt" wird
 */
class Dispatcher {

    private Set<ClientEndpoint> connectedClients = new TreeSet<>();
    private Lock monitor = new ReentrantLock();


    public void addClient(ClientEndpoint client) {
        this.monitor.lock();
        dispatchAdminMessage(client.username + " has entered the chat. Welcome!");
        this.connectedClients.add(client);
        this.monitor.unlock();
    }

    public void removeClient(ClientEndpoint client) {
        this.monitor.lock();
        this.connectedClients.remove(client);
        dispatchAdminMessage(client.username + " has left the chat. Good Bye!");
        this.monitor.unlock();
    }

    public void dispatchMessage(String message, ClientEndpoint sender) {
        this.monitor.lock();
        for(ClientEndpoint receiver : this.connectedClients) {
            if( !receiver.equals(sender) ) {
                receiver.writer.println("SHOW#" + sender.username + "#" + message);
                receiver.writer.flush();
            }
        }
        this.monitor.unlock();
    }

    public void dispatchAdminMessage(String message) {
        this.monitor.lock();
        for(ClientEndpoint receiver : this.connectedClients) {
            receiver.writer.println("ADMN#" + message);
            receiver.writer.flush();
        }
        this.monitor.unlock();
    }



}

class ClientEndpoint implements Runnable, Comparable<ClientEndpoint> {

    private Socket socket;
    protected PrintWriter writer;
    protected BufferedReader reader;
    private Dispatcher dispatcher;  // das geteilte, passive Objekt
    protected String username;
    private boolean running = true;

    public ClientEndpoint(Socket socket, Dispatcher dispatcher) {
        this.socket = socket;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        try {
            this.writer = new PrintWriter(socket.getOutputStream());
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            // Thread wartet, Client muss OPEN als erster schicken
            String openMessage = reader.readLine();
            if(openMessage == null) {
                return;
            }
            if(!openMessage.startsWith("OPEN#")) {
                this.writer.print("ADMN#Error, client has to send OPEN followed by HASH and USERNAME as first message. Socket will be closed by server.");
                this.writer.flush();
                this.socket.close();
                return;
            }

            String[] openMessageParts = openMessage.split("#");
            this.username = openMessageParts[1].trim();
            this.dispatcher.addClient(this);
            this.writer.println("ADMN#Welcome " + this.username + "!");
            this.writer.flush();

            System.out.println("LOG:New Client for " + this.username + " added");

            // Nach OPEN auf eingehende Nachrichten des Clients warten
            while(this.running) {
                String empfangen = reader.readLine();

                System.out.println("LOG:New String received: " + empfangen);

                if(empfangen == null) { // User hat "leere" Nachricht geschickt
                    this.running = false;
                    this.dispatcher.removeClient(this);
                }
                else if(empfangen.startsWith("EXIT")) {
                    this.running = false;
                    this.dispatcher.removeClient(this);
                }
                else if(empfangen.startsWith("PUBL#")) {
                    String[] parts = empfangen.split("#");
                    this.dispatcher.dispatchMessage(parts[1], this);
                }
                else {
                    this.writer.println("ADMN#Error, wrong command: " + empfangen);
                    this.writer.flush();
                }
            }

        }
        catch (Exception e) {
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

    
    // Hilfsfunktionen noetig, damit die Klasse ClientEndpoint in einem Set<ClientEndpoint> verwaltet werden kann
    // (nicht relevant fuer die Themen Socket und Threads --> kann ignoriert werden)
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