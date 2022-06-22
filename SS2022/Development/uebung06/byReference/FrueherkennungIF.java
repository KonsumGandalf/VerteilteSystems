package Development.uebung06.byReference;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueherkennungIF extends Remote {
    public BerichtIF analysieren(RoentgenbildIF r) throws RemoteException;
}
