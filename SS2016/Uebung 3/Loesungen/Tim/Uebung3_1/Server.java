package SS2016.Uebung;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String [] args){
		try(ServerSocket servSocket = new ServerSocket(1234)){
			//blockiert bei accept() bis Client::connect:
				try(Socket s = servSocket.accept()){
					while(true){
						//Senden und Empfangen �ber Streams undReader/Writer
						InputStream in = s.getInputStream();
						OutputStream out = s.getOutputStream();

						PrintWriter writer = new PrintWriter(out);
						writer.println("Das ist ein Test");
						writer.flush();

						writer.println("Das ist ein Test2");
						writer.flush();

						BufferedReader reader = new BufferedReader(new InputStreamReader(in));
						String antwort = reader.readLine();
						if(antwort!=null){
							System.out.println(antwort);
						}

						String antwort2 = reader.readLine();
						if(antwort2!=null){
							System.out.println(antwort2);
						}
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}