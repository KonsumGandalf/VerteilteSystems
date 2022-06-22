package SS2016.Uebung;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueherkennungIF extends Remote {

    public void analysieren(Roentgenbild bild, BerichtCallbackIF callback) throws RemoteException;
    
}
