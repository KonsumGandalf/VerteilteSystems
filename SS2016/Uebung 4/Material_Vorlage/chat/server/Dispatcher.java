package SS2016.Uebung;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Dispatcher extends Thread {

	private int port;
	private IChatMessageHub hub;
	
	public Dispatcher(int port, IChatMessageHub hub) {
		this.port = port;
		this.hub = hub;
		start();
	}
	
	// Überschreiben Sie die run-Methode hier:





}
