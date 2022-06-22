package SS2016.Uebung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerEndpoint extends Thread implements IChatListener {

	private String name;
	private Socket socket;
	private IChatMessageHub hub;
	private BufferedReader reader;
	private PrintWriter writer;
		
	public ServerEndpoint(Socket socket, IChatMessageHub hub) {
		this.socket = socket;
		this.hub = hub;
		// ggf. weitere Implementierung



		// als Thread starten
		start();
	}
	
	// Ueberschreiben Sie alle notwendigen Methoden inkl. run() hier:





}
