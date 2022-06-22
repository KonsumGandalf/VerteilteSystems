package Development.uebung06.byCallback;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface CallbackIF extends Remote {
    public void setBestaetigung(String name, BerichtIF berichtIF) throws RemoteException;
}
