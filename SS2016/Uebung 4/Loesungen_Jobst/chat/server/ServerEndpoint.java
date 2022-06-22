package SS2016.Uebung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class ServerEndpoint extends Thread implements IChatListener {

	private String name;
	private Socket socket;
	private IChatMessageHub hub;
	private BufferedReader reader;
	private PrintWriter writer;
	private boolean opened = false;
	private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
		
	public ServerEndpoint(Socket socket, IChatMessageHub hub) {
		this.socket = socket;
		this.hub = hub;

		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		// als Thread starten
		start();
	}
	
	@Override
	public void run() {
		try {
			
			String line="";
			do {
				line = reader.readLine();
				if(line == null)
					continue;
				if("".equals(null))
					continue;
				log.info("received " + line);
				String[] parts = line.split("#");

				if(opened) {
					if(parts[0].equals("PUBL") && parts.length > 1)
						hub.publish(this.name, parts[1], false);
				}
				else if(parts[0].equals("OPEN")) {
					if(parts[1] != null)
						this.name = parts[1];
					else
						throw new RuntimeException("OPEN without username");
					hub.addChatListener(this);
					log.info("New client with username " + this.name);

					opened = true;
				}
				if(parts[0].equals("EXIT")) {
					writer.close();
					reader.close();
					socket.close();
					opened = false;
				}
				// else ignore whatever came, no protocol command
			} while( opened );
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			hub.removeChatListener(this);
			try {
				writer.close();
				reader.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		log.info("Thread for " + name + " ended");
	}

	@Override
	public void onMessage(String user, String message, boolean isAdmin) {
		if(isAdmin)
			writer.write("ADMN#" + message + "\n");
		else
			writer.write("SHOW#" + user + "#" + message + "\n");
		writer.flush();
	}

	@Override
	public String getUsername() {
		return this.name;
	}
}
