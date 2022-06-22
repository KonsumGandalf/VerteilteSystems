package Development.uebung04.a_messager;

import java.io.*;
import java.net.ServerSocket;
import java.util.List;
import java.net.Socket;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable{
    private Socket socket;
    public static int SERVER_PORT = 1213;
    private MessageStore messages;
    private Logger logger = Logger.getLogger("MESSAGE SERVER");

    public Server(Socket socket, MessageStore msg){
        this.socket = socket;
        this.messages = msg;
    }

    @Override
    public void run(){
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)){
            InputStream in;
            OutputStream out;
            BufferedReader reader;
            PrintWriter writer;
            String thisLine;

            while (true) {
                Socket client = serverSocket.accept();

                in = client.getInputStream();
                out = client.getOutputStream();
                reader = new BufferedReader(new InputStreamReader(in));
                writer = new PrintWriter(out);

                writer.println("You are sending from " + socket.getRemoteSocketAddress().toString());
                writer.println("Commands allowed are:");
                writer.println("REG<username>");
                writer.println("SND<from>#<to>#<text>");
                writer.println("RCV<username>");
                writer.println();
                writer.flush();

                String requestString = reader.readLine();
                String command = requestString.substring(0, 3).trim();

                String paramString = requestString.substring(3).trim();
                String[] params = paramString.split("#");
                String username = params[0].trim();
                System.out.println(requestString);

                String answer;
                switch (command) {
                    case "REG" : {
                        if (messages.registerUser(username)) answer = "The User was registered successfully :)";
                        else answer = "ERROR!\nThe User was NOT found or ur Account is false";
                        break;
                    }
                    case "SND" : {
                        if (messages.sendMsg(params[2], username, params[1])) answer = "The Msg was sent successfully :)";
                        else answer = "ERROR!\nThe Msg was NOT sent";
                        break;
                    }
                    case "RCV" : {
                        List <Message> msg = messages.getNewMessages(username);
                        if (!msg.isEmpty()) answer = "The Msg was sent successfully :)";
                        else answer = "ERROR!\nThe Msg was NOT sent";
                        break;
                    }
                    default: {
                        answer = "wrong command " + command;
                    }
                }
                writer.println("The request was processed: " + requestString);
                writer.println(answer);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
