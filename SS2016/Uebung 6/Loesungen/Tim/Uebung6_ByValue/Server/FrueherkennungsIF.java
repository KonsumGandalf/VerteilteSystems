package SS2016.Uebung;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueherkennungsIF extends Remote{
	
	Bericht analysieren(Roentgenbild roentgenBild) throws RemoteException;
}
