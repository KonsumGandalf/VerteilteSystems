package server.service;

import server.entity.Message;
import server.repository.MessageStore;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class ClientRequest implements Runnable {

    private Socket socket;
    private MessageStore messageStore;
    private Logger logger = Logger.getLogger("MESSAGE SERVER");

    public ClientRequest(Socket socket, MessageStore messageStore) {
        this.socket = socket;
        this.messageStore = messageStore;
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "New connection from " + this.socket.getRemoteSocketAddress().toString());

        try(InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream() ) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            PrintWriter writer = new PrintWriter(out);

            String request = "";
            try {
                writer.println("Welcome to OTH messaging service.");
                writer.println("You are sending from " + socket.getRemoteSocketAddress().toString());
                writer.println("Commands allowed are:");
                writer.println("REG<username>");
                writer.println("SND<from>#<to>#<text>");
                writer.println("RCV<username>");
                writer.println();
                writer.flush();

                request = reader.readLine();

                String command = request.substring(0, 3).trim();

                String paramString = request.substring(3).trim();
                String[] params = paramString.split("#");
                String username = params[0].trim();

                if(command.equals("REG")) {
                    String user = messageStore.addUser(username);
                    if(user == null)
                        writer.println("ERRCould not add user. Username already exists?");
                    else
                        writer.println("OK " + user);
                    writer.flush();
                } else if(command.equals("SND")) {
                    String an = params[1].trim();
                    String text = params[2].trim();
                    Message message = messageStore.addMessage(username, an, text);
                    writer.println("OK " + message);
                    writer.flush();
                } else if(command.equals("RCV")) {
                    List<Message> messages = messageStore.getNewMessages(username);
                    if(messages == null) {
                        writer.println("ERRNo such username");
                    }
                    else {
                        writer.println("OK " + messages.size());
                        for (Message message : messages)
                            writer.println("OK " + message);
                        writer.flush();
                    }
                } else {
                    throw new IllegalArgumentException("wrong command " + command);
                }
            } catch (Exception ex) {
                writer.println("ERRProblem with received request. Request was: " + request);
                writer.flush();
                logger.log(Level.INFO, "Problem with received request. Error message was: " + ex.getMessage(), ex);
            }


        } catch(IOException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        logger.log(Level.INFO, "Ended connection from " + this.socket.getRemoteSocketAddress().toString());
    }
}
