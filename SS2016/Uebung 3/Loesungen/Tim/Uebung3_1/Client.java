package SS2016.Uebung;

import java.io.*;
import java.net.Socket;

public class Client {
	public static void main(String [] args){
		while(true){
			try(Socket s = new Socket("172.16.33.147", 1234)){
				while(true){
					//Senden und Empfangen �ber Streams undReader/Writer
					InputStream in = s.getInputStream();
					OutputStream out = s.getOutputStream();

					PrintWriter writer = new PrintWriter(out);
					writer.println("Hallo");
					writer.flush();

					writer.println("Hallo2");
					writer.flush();

					BufferedReader reader = new BufferedReader(new InputStreamReader(in));

					String antwort = reader.readLine();
					if (antwort!=null){
						System.out.println(antwort);
					}

					String antwort2 = reader.readLine();
					if (antwort2!=null){
						System.out.println(antwort2);
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
}

