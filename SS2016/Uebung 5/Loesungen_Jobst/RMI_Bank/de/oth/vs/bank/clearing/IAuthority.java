package SS2016.Uebung;

import java.rmi.Remote;
import java.rmi.RemoteException;

import de.oth.vs.bank.server.Cheque;

public interface IAuthority extends Remote {
	public void requestApproval(Cheque scheck) throws RemoteException;
	public boolean obtainApproval() throws RemoteException;
}
