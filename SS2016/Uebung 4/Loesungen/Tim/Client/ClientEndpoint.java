package SS2016.Uebung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientEndpoint extends Thread implements IMessageSender {

	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	private IMessageGui messageGui;
	private boolean closePending;

	public ClientEndpoint() {

	}

	@Override
	public void openChatConnection(String username, String host, int port, IMessageGui messageGui) {
		try {
			socket = new Socket(host, port);
			this.messageGui = messageGui;
			writer = new PrintWriter(socket.getOutputStream());
			writer.println("OPEN#"+ username);
			writer.flush();
			this.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void sendChatMessage(String message) {
		try {
			if(message.equals("")){
				messageGui.showMessageDialog("Nachricht ist leer!", "Nachricht leer...");
			}
			else if(message.contains("#")){
				messageGui.showMessageDialog("Nachricht darf kein # enthalten", "Nachricht darf kein # enthalten");
			}
			else{
				writer = new PrintWriter(socket.getOutputStream());
				writer.println("PUBL#"+ message);
				writer.flush();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void closeChatConnection() {
		try {
			writer.write("EXIT\n");
			writer.flush();
			closePending = true;
			Thread.sleep(100);
			socket.close();
			this.interrupt();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void run(){
		try {
			while(true){
				if(closePending){
					break;
				}
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				interprete(reader.readLine());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void interprete(String message){
		try{
			String [] newMessage = message.split("#");
			String temp = "";
			switch(newMessage[0]){
			case "ADMN":
				for(int i=1; i<newMessage.length; i++){
					if(!newMessage[i].equals("")){
						temp = temp + newMessage[i];
					}
				}
				messageGui.showAdminMessage(temp);
				messageGui.scrollDown();
				break;
			case "SHOW":
				for(int i=2; i<newMessage.length; i++){
					temp = temp + newMessage[i];
				}
				messageGui.showNewMessage(newMessage[1], temp);
				messageGui.scrollDown();
				break;
			default:
				break;
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
