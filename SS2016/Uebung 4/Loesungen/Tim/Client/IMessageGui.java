package SS2016.Uebung;

public interface IMessageGui {
	public void showNewMessage(String fromUser, String message);
	public void showAdminMessage(String message);
	public void showMessageDialog(String message, String title);
	public void scrollDown();
}
