package Development.uebung03.c_simple_sockets;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class KeyboardListener implements Runnable{
    private Socket socket;

    KeyboardListener(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try{
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out);

            Scanner keyboard = new Scanner(System.in);

            System.out.println("Please insert your nickname");
            String name = keyboard.nextLine();
            writer.println(name + " joined the chat");
            writer.flush();

            String input = null;
            do {
                input = keyboard.nextLine();
                writer.println(name+" >"+input);
                writer.flush();
            } while (input != null && !input.equalsIgnoreCase(Server.END_STRING));
        } catch (IOException e){
            System.out.println("the keyboard");
            e.printStackTrace();
        }
    }

}
