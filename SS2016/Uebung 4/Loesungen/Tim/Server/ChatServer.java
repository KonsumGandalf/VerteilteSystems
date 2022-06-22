package SS2016.Uebung;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

public class ChatServer implements IChatMessageHub {
	
	private Map<String, IChatListener> listeners = new HashMap<>();
	private Logger log = Logger.getLogger(this.getClass().getSimpleName());
	
	public ChatServer() {
	}

	
	@Override
	public synchronized void addChatListener(IChatListener listener) {
		if( listeners.containsKey(listener.getUsername()) ) {
			System.out.println("Duplicate username " + listener.getUsername());
			listener.onMessage(null, "Duplicate username " + listener.getUsername() + ".", true);
			listener.onMessage(null, "Please restart and try again!", true);
			return;
		}
		listeners.put(listener.getUsername(), listener);

		StringBuilder message = new StringBuilder();
		message.append("Welcome ");
		message.append(listener.getUsername());
		message.append("!");
		publishPrivate(listener.getUsername(), message.toString(), true);

		Set<String> usernames = listeners.keySet();
		if(usernames == null || usernames.size() <= 1)
			publishPrivate(listener.getUsername(), "Chat room is empty, you are the first member.", true);
		else {
			StringBuilder members = new StringBuilder();
			members.append("Users online: ");
			int count = 2;
			for(String user : usernames) {
				if(user.equals(listener.getUsername()))
					continue;
				members.append(user);
				if(usernames.size() > count++)
					members.append(", ");
			}
			publishPrivate(listener.getUsername(), members.toString(), true);
		}
		publish(listener.getUsername(), listener.getUsername() + " has entered the chat. Welcome!", true);
		log.info("added chat listener for username " + listener.getUsername());
	}
	
	@Override
	public synchronized void removeChatListener(IChatListener listener) {
		listeners.remove(listener.getUsername());
		log.info("removed chat listener for username " + listener.getUsername());
		publish(listener.getUsername(), listener.getUsername() + " has left the chat. Good Bye!", true);
	}
	
	@Override
	public synchronized void publish(String fromUser, String message, boolean isAdmin) {
		
		for(Entry<String, IChatListener> l : listeners.entrySet()) {
			if( !(l.getKey().equals(fromUser)) ) {
				l.getValue().onMessage(fromUser, message, isAdmin);
			}
		}
	}
	
	private synchronized void publishPrivate(String toUser, String message, boolean isAdmin) {
		IChatListener client = listeners.get(toUser);
		if(client == null)
			return;
		client.onMessage(null, message, isAdmin);
	}


}
