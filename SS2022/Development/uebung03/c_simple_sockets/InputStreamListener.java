package Development.uebung03.c_simple_sockets;

import java.io.*;
import java.net.Socket;

public class InputStreamListener implements Runnable {
    private Socket socket;

    public InputStreamListener(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            InputStream in = this.socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String input = null;
            do{
                input = reader.readLine();
                System.out.println(input);
            } while (input != null && !input.equalsIgnoreCase(Server.END_STRING));
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error with reading the InputStream");
            e.printStackTrace();
        }
    }
}
