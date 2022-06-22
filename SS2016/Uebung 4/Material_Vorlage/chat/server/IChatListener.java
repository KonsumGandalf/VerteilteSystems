package SS2016.Uebung;

public interface IChatListener {
	public void onMessage(String fromUser, String message, boolean isAdmin);
	public String getUsername();
}
