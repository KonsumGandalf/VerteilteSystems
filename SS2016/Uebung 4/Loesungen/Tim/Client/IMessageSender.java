package SS2016.Uebung;

public interface IMessageSender {
	public void openChatConnection(String username, String host, int port,
			IMessageGui messageGui);
	public void sendChatMessage(String message);
	public void closeChatConnection();
}
