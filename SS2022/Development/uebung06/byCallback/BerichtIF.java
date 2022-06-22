package Development.uebung06.byCallback;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BerichtIF extends Remote {
    String getRepresentation() throws RemoteException;
}
