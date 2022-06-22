package Development.uebung06.byValue;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueherkennungIF extends Remote {
    public Bericht analysieren(Roentgenbild r) throws RemoteException;
}
