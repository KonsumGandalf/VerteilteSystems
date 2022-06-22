package SS2016.Uebung;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.Future;

public interface FrueherkennungIF extends Remote {

    public Future<Bericht> analysieren(Roentgenbild bild) throws RemoteException;
    
}
