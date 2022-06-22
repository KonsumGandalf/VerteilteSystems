package Development.uebung06.lukas.c;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallbackIF extends Remote {

    public void onBestaetigung(Bericht b) throws RemoteException;

}
