package util;

import server.Server;

import java.io.*;
import java.net.Socket;

public class InputStreamListener implements Runnable {

    private Socket socket;

    public InputStreamListener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream in  = this.socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String input = null;

            do {
                // wait for socket to receive from other end...
                input = reader.readLine(); // blocks until socket receives a string from other end
                // ...and print to local console
                System.out.println(">" + input);

            } while(input != null && !input.equalsIgnoreCase(Server.END_STRING));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
