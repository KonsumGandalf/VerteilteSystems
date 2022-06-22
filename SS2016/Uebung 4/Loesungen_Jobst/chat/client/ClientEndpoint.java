package SS2016.Uebung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Logger;

public class ClientEndpoint extends Thread implements IMessageSender {

	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	private IMessageGui messageGui;
	private Logger log = Logger.getLogger(this.getClass().getSimpleName());

	public ClientEndpoint() {
		
	}
	
	@Override
	public void run() {
		while(!socket.isClosed()) {
			try {
				String line = reader.readLine();
				log.info("Received: " + line);
				String[] parts = null;
				if(line != null)
					parts = line.split("#");
				if(parts == null || parts[0]==null || parts[0].equals("EXIT")) {
					reader.close();
					writer.close();
					socket.close();
				}
				else if(parts[0].equals("ADMN"))
					messageGui.showAdminMessage(parts[1]);
				else if(parts[0].equals("SHOW"))
					messageGui.showNewMessage(parts[1], parts[2]);
			} catch (IOException e) {
				if(!e.getMessage().equals("Socket closed"))
					e.printStackTrace();
			}
			
		} 
		log.info("Socket closed...");
	}
	
	@Override
	public void sendChatMessage(String message) {
		writer.write("PUBL#" + message + "\n");
		writer.flush();
		log.info("PUBL#" + message);
	}

	@Override
	public void openChatConnection(String username, String host, int port, IMessageGui messageGui) {
		this.messageGui = messageGui;
		try {
			socket = new Socket(host, port);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			writer.write("OPEN#" + username + "\n");
			writer.flush();
			start();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	@Override
	public void closeChatConnection() {
		writer.write("EXIT\n");
		writer.flush();
		writer.close();
		try {
			reader.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
