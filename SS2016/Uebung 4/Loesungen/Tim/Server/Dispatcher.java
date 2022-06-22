package SS2016.Uebung;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Dispatcher extends Thread {

	private int port;
	private IChatMessageHub hub;
	private Logger log = Logger.getLogger(this.getClass().getSimpleName());
	
	public Dispatcher(int port, IChatMessageHub hub) {
		this.port = port;
		this.hub = hub;
		start();
	}
	
	@Override
	public void run() {
		try ( ServerSocket serverSocket = new ServerSocket(port) ) {
			log.info("Dispatcher listening...");
			while( !this.isInterrupted() ) {
				Socket newSocket = serverSocket.accept();
				log.info("New connection accepted from " + newSocket.getInetAddress().getHostAddress());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
