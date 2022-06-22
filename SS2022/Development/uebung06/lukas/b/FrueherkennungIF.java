package Development.uebung06.lukas.b;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueherkennungIF extends Remote {

    public BerichtIF analysieren(Roentgenbild rb) throws RemoteException;
}
