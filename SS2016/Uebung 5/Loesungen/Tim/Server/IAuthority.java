package SS2016.Uebung;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAuthority extends Remote {
	public void requestApproval(Cheque scheck) throws RemoteException;
	public boolean obtainApproval() throws RemoteException;
}
