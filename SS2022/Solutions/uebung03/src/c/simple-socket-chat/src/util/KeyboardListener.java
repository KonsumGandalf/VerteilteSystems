package util;

import server.Server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class KeyboardListener implements Runnable {
    private Socket socket;

    public KeyboardListener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out);

            Scanner keyboard = new Scanner(System.in);
            String input = null;

            do {
                // wait for user to type a line...
                input = keyboard.nextLine(); // blocks until enter-key is pressed on keyboard
                // ...and sent via socket to other end
                writer.println(input);
                writer.flush();

            } while(input != null && !input.equalsIgnoreCase(Server.END_STRING));

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
