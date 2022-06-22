package SS2016.Uebung;

public class Server {
	public static final int SERVER_PORT = 1234;
	
	public static void main(String[] args) {
		IChatMessageHub hub = new ChatServer();
		int port = SERVER_PORT;
		if(args.length > 0)
			if(Integer.parseInt(args[0]) > 1024)
				port = Integer.parseInt(args[0]);
		new Dispatcher(port, hub);
	}
}
