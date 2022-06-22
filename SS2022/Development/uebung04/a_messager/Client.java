package Development.uebung04.a_messager;

import Solutions.uebung_04.a_04_messaging.serverPack.MessagingService;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable{
    private static String SERVER_NAME = "localhost";
    public static int SERVER_PORT = Server.SERVER_PORT;
    private Logger logger = Logger.getLogger("MESSAGE SERVER");
    private Socket socket;
    Client(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream in;
        OutputStream out;
        BufferedReader reader;
        PrintWriter writer;
        String thisLine;
        boolean stayInMsg = false;
        try {
            Socket server = new Socket(SERVER_NAME, SERVER_PORT);
            in = server.getInputStream();
            out = server.getOutputStream();
            reader = new BufferedReader(new InputStreamReader(in));
            writer = new PrintWriter(out);
            Scanner keyboard = new Scanner(System.in);
            do {
                // 1. Welcome msg
                while ((thisLine = reader.readLine()) != null) {
                    System.out.println(thisLine);
                }

                // 2. Command of the user
                String cmdUser = keyboard.nextLine();
                writer.println(cmdUser);
                writer.flush();

                System.out.println("Do u want to continue messaging?");
                String response = keyboard.nextLine();
                stayInMsg = response.contains("Yes") || response.equalsIgnoreCase("Y");
            } while (stayInMsg);
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        logger.log(Level.INFO, "Ended connection from " + this.socket.getRemoteSocketAddress().toString());
    }

    public static void main(String[] args) {InputStream in;
        OutputStream out;
        BufferedReader reader;
        PrintWriter writer;
        String thisLine;
        boolean stayInMsg = false;
        try {
            Socket server = new Socket(SERVER_NAME, SERVER_PORT);
            in = server.getInputStream();
            out = server.getOutputStream();
            reader = new BufferedReader(new InputStreamReader(in));
            writer = new PrintWriter(out);
            Scanner keyboard = new Scanner(System.in);
            do {
                // 1. Welcome msg
                while ((thisLine = reader.readLine()) != null) {
                    System.out.println(thisLine);
                }

                // 2. Command of the user
                String cmdUser = keyboard.nextLine();
                writer.println(cmdUser);
                writer.flush();

                System.out.println("Do u want to continue messaging?");
                String response = keyboard.nextLine();
                stayInMsg = response.contains("Yes") || response.equalsIgnoreCase("Y");
            } while (stayInMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
