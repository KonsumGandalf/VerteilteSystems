package Development.uebung06.byCallback;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueherkennungIF extends Remote {
    public void analysieren(RoentgenbildIF r, CallbackIF callbackIF) throws RemoteException;
}
