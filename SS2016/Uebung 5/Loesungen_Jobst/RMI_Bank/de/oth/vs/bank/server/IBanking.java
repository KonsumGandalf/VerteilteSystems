package SS2016.Uebung;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBanking extends Remote {
	
	public void withdraw(int cents) throws RemoteException;
	public void deposit(int cents) throws RemoteException;
	public void deposit(Cheque scheck) throws RemoteException;
	
}
