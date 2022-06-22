package client;

import util.InputStreamListener;
import util.KeyboardListener;

import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1212);

            new Thread(new InputStreamListener(socket)).start();
            new Thread(new KeyboardListener(socket)).start();

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

}
