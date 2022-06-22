package Development.uebung03.c_simple_sockets_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class InputStreamListener implements Runnable{
    private Socket socket;
    InputStreamListener(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            InputStream in  = this.socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String input = null;

            do {
                // wait for socket to receive from other end...
                input = reader.readLine(); // blocks until socket receives a string from other end
                // ...and print to local console
                System.out.println(">" + input);

            } while(input != null && !input.equalsIgnoreCase(server.Server.END_STRING));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
